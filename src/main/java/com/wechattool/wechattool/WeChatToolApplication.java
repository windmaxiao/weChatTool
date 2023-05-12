package com.wechattool.wechattool;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author maxiao
 */
@SpringBootApplication
@MapperScan("com.wechattool.wechattool.mapper")
public class WeChatToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeChatToolApplication.class, args);
    }

}
