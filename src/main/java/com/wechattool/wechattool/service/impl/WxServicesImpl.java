package com.wechattool.wechattool.service.impl;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.wechattool.wechattool.model.AccessToken;
import com.wechattool.wechattool.model.DataWxApp;
import com.wechattool.wechattool.service.AccessTokenService;
import com.wechattool.wechattool.service.IDataWxAppService;
import com.wechattool.wechattool.service.IDataWxMsgService;
import com.wechattool.wechattool.service.WxServices;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author maxiao
 */
@Service
@Slf4j
public class WxServicesImpl implements WxServices {

    private static final String ACCESS_TOKEN_URL= "https://api.weixin.qq.com/cgi-bin/token";
    @Autowired
    IDataWxAppService dataWxAppService;

    @Autowired
    IDataWxMsgService dataWxMsgService;

    @Autowired
    AccessTokenService accessTokenService;

    @Override
    public String checkSignature(String signature, String timestamp, String nonce, String echostr) {

        DataWxApp dataWxApp = dataWxAppService.getWxData();

        String token = dataWxApp.getToken();
        List<String> list = new ArrayList<>();
        list.add(token);
        list.add(timestamp);
        list.add(nonce);

        list = list.stream().sorted().collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
        }

        String val = sb.toString();
        String password = SecureUtil.sha1(val);


        if (StringUtils.equals(password, signature)) {
            return echostr;
        }
        return null;
    }

    @Override
    public void getAccessToken() {

        DataWxApp dataWxApp = dataWxAppService.getWxData();

        String url = ACCESS_TOKEN_URL + "?"
                + "grant_type=client_credential"
                + "&appid=" + dataWxApp.getAppId()
                + "&secret=" + dataWxApp.getAppSecret();

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .build();

        String responseData = "";
        try {
            Response response = client.newCall(request).execute();
            responseData = Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            log.info("获取AccessToken异常 : {}", e.getMessage());
        }

        log.info("获取了AccessToken : {}", responseData);

        JSONObject json = JSONUtil.parseObj(responseData);
        AccessToken accessToken = json.toBean(AccessToken.class);
        accessToken.setCreateTime(LocalDateTime.now());
        accessToken.setExpiresTime(accessToken.getCreateTime().plusSeconds(accessToken.getExpiresIn()));
        accessTokenService.save(accessToken);

    }

}
