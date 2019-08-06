package com.haiyu.manager.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @ Author     : wzt.
 * @ Date       : Created in 18:46 2019/8/5
 * @ Description: 异步线程池配置类
 * @ Modified By:
 * @ Version    : 1.0$
 */
@Configuration
public class ThreadPoolExecutorConfig {
    private static final int THREADS = Runtime.getRuntime().availableProcessors() + 1;
    final ThreadFactory threadFactory = new ThreadFactoryBuilder()
            // -%d不要少
            .setNameFormat("async-task-name-%d")
            .setDaemon(true)
            .build();

    @Bean("taskExecutor")
    public Executor taskExecutor() {
        return new ThreadPoolExecutor(THREADS,
                2 * THREADS,
                5, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1024),
                threadFactory, (r, executor) -> {
            // 打印日志,添加监控等
            System.out.println("task is rejected!");
        });
    }
}
