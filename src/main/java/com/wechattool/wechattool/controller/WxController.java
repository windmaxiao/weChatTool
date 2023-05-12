package com.wechattool.wechattool.controller;

import com.wechattool.wechattool.service.IDataWxAppService;
import com.wechattool.wechattool.service.IDataWxMsgService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author maxiao
 */
@RestController
@RequestMapping("/wx")
public class WxController {

    @Autowired
    IDataWxAppService dataWxAppService;
    @Autowired
    IDataWxMsgService dataWxMsgService;


    @GetMapping("/")
    public String checkWx() {
        return "";
    }


    @GetMapping("/getMsg")
    public String getMsg() {

        return dataWxMsgService.getById("1").toString();
    }

    @GetMapping("/getApp")
    public String getApp() {

        return dataWxAppService.getById("1").toString();
    }
}
