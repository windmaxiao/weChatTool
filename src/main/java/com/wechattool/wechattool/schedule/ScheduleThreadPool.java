package com.wechattool.wechattool.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author windxiao
 */
@Slf4j
@EnableAsync
@Configuration
public class ScheduleThreadPool {

    @Bean(name = "asyncScheduleServiceExecutor")
    public Executor asyncScheduleServiceExecutor() {

        // SpringBoot项目，可使用Spring提供的对 ThreadPoolExecutor 封装的线程池 ThreadPoolTaskExecutor：
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        // 配置核心线程数
        executor.setCorePoolSize(5);

        // 配置最大线程数
        executor.setMaxPoolSize(10);

        // 配置队列大小
        executor.setQueueCapacity(20);

        // 配置线程池中的线程的名称前缀(方便排查问题)
        executor.setThreadNamePrefix("async-scheduler-service-");

        // 配置线程拒绝策略
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // 1.CallerRunsPolicy:不在新线程中执行任务，而是由调用者所在的线程来执行。
        //   "该策略既不会抛弃任务，也不会抛出异常，而是将任务回推到调用者。"顾名思义，在饱和的情况下，调用者会执行该任务（而不是由多线程执行）
        // 2.AbortPolicy:拒绝策略，直接拒绝抛出异常
        // 3.DiscardPolicy:丢弃任务，但是不抛出异常。可以配合这种模式进行自定义的处理方式
        // 4.DiscardOldestPolicy:丢弃队列最早的未处理任务，然后重新尝试执行任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        //执行初始化
        executor.initialize();

        return executor;
    }

}

