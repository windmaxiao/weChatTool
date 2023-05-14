package com.wechattool.wechattool.controller;

import com.wechattool.wechattool.model.DataWxMsg;
import com.wechattool.wechattool.service.IDataWxMsgService;
import com.wechattool.wechattool.service.WxServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


/**
 * @author maxiao
 */
@RestController
@RequestMapping("/wx")
@Slf4j
public class WxController {


    @Autowired
    WxServices wxServices;

    @Autowired
    IDataWxMsgService dataWxMsgService;


    /**
     *
     * @param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @param echostr 随机字符串
     * @return
     */
    @GetMapping("/verify")
    public String checkWx(@RequestParam("signature") String signature,
                          @RequestParam("timestamp") String timestamp,
                          @RequestParam("nonce") String nonce,
                          @RequestParam("echostr") String echostr) {


        DataWxMsg msg = new DataWxMsg();
        msg.setTime(LocalDateTime.now());
        msg.setMsg("signature=" + signature + ";timestamp=" + timestamp + ";nonce=" + nonce + ";echostr=" + echostr);

        dataWxMsgService.save(msg);

        return wxServices.checkSignature(signature, timestamp, nonce, echostr);
    }

}
