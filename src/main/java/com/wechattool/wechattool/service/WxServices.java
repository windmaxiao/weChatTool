package com.wechattool.wechattool.service;

/**
 * @author maxiao
 */
public interface WxServices {

    /**
     * 验证微信token
     * @param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @param echostr 随机字符串
     * @return echostr
     */
    String checkSignature(String signature, String timestamp, String nonce, String echostr);

}
