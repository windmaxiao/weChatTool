package com.wechattool.wechattool.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author maxia
 */
@Slf4j
public class WxSchedule {


    @Async(value = "asyncScheduleServiceExecutor")
    @Scheduled(cron = "0 0 0/2 * * ?")
    public void wxGetAccessToken() throws InterruptedException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
        log.info("wxGetAccessToken - 执行定时任务【0 0 0/2 * * ?】，时间： " + dateFormat.format(new Date()));

    }

}
