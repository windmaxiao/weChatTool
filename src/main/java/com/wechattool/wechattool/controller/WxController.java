package com.wechattool.wechattool.controller;

import com.wechattool.wechattool.model.DataWxMsg;
import com.wechattool.wechattool.model.WxMessage;
import com.wechattool.wechattool.service.IDataWxMsgService;
import com.wechattool.wechattool.service.WxServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.Callable;


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
    @GetMapping()
    public String checkWx(@RequestParam("signature") String signature,
                          @RequestParam("timestamp") String timestamp,
                          @RequestParam("nonce")     String nonce,
                          @RequestParam("echostr")   String echostr) {

        DataWxMsg msg = new DataWxMsg();
        msg.setTime(LocalDateTime.now());
        msg.setMsg("signature=" + signature + ";timestamp=" + timestamp + ";nonce=" + nonce + ";echostr=" + echostr);
        msg.setSource(this.getClass().getName() + ":checkWx");

        dataWxMsgService.save(msg);

        return wxServices.checkSignature(signature, timestamp, nonce, echostr);
    }

    @PostMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public Object receiveMessages(@RequestBody WxMessage requestMessage) {

        log.info("微信发消息了：{}", requestMessage.toString());

        Callable<String> result = () -> {
            log.info("副线程先返回成功：{}", Thread.currentThread().getName());
            return "success";
        };


        DataWxMsg msg = new DataWxMsg();
        msg.setTime(LocalDateTime.now());
        msg.setMsg(requestMessage.toString());
        msg.setSource(this.getClass().getName() + ":receiveMessages");
        dataWxMsgService.save(msg);

        // TODO 接收返回消息
        String fromUserName = requestMessage.getFromUserName();
        String toUserName = requestMessage.getToUserName();

        //新建一个响应对象
        WxMessage responseMessage = new WxMessage();
        //消息来自谁
        responseMessage.setFromUserName(toUserName);
        //消息发送给谁
        responseMessage.setToUserName(fromUserName);
        //消息类型，返回的是文本
        responseMessage.setMsgType("text");
        //消息创建时间，当前时间就可以
        responseMessage.setCreateTime(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
        //这个是响应消息内容，直接复制收到的内容做演示，甚至整个响应对象都可以直接使用原请求参数对象，只需要换下from和to就可以了哈哈哈
        responseMessage.setContent(requestMessage.getContent());

        return responseMessage;
    }

}
