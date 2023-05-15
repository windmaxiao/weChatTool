package com.wechattool.wechattool.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.wechattool.wechattool.model.DataWxApp;
import com.wechattool.wechattool.model.DataWxMsg;
import com.wechattool.wechattool.service.IDataWxAppService;
import com.wechattool.wechattool.service.IDataWxMsgService;
import com.wechattool.wechattool.service.WxServices;
import com.wechattool.wechattool.tools.OkHttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

        String response = OkHttpClientUtils.builder().url(ACCESS_TOKEN_URL)
                .addParam("grant_type", "client_credential")
                .addParam("appid", dataWxApp.getAppId())
                .addParam("secret", dataWxApp.getAppSecret())
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .get().sync();

        DataWxMsg msg = new DataWxMsg();
        msg.setTime(LocalDateTime.now());
        msg.setMsg(response);
        msg.setSource(this.getClass().getName());

        dataWxMsgService.save(msg);

    }
}
