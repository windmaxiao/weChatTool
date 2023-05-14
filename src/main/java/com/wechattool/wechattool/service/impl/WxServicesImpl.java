package com.wechattool.wechattool.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.wechattool.wechattool.model.DataWxApp;
import com.wechattool.wechattool.service.IDataWxAppService;
import com.wechattool.wechattool.service.IDataWxMsgService;
import com.wechattool.wechattool.service.WxServices;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public String checkSignature(String signature, String timestamp, String nonce, String echostr) {

        DataWxApp dataWxApp = dataWxAppService.getById("1");

        String token = dataWxApp.getToken();
        List<String> list = new ArrayList<String>();
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

        DataWxApp dataWxApp = dataWxAppService.getById("1");

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(ACCESS_TOKEN_URL + "?grant_type=client_credential&appid="
                + dataWxApp .getAppId() + "&secret=" + dataWxApp.getAppSecret()).build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            log.info("返回的数据：" + response.body().string());
            // TODO save response
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
