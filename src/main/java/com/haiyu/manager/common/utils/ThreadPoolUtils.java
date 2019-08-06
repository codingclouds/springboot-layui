package com.haiyu.manager.common.utils;

import com.haiyu.manager.dao.LogMapper;
import com.haiyu.manager.pojo.LogPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @ Author     : wzt.
 * @ Date       : Created in 19:12 2019/8/5
 * @ Description: 异步线程池工具类
 * @ Modified By:
 * @ Version    : 1.0$
 */
@Component
public class ThreadPoolUtils {

    @Autowired
    private LogMapper logMapper;

    //异步线程池写日志
    @Async("taskExecutor")
    public Future<Object> addLog(LogPojo logPojo, Object object)  {
        System.out.println("MsgServer send A thread name->" + Thread.currentThread().getName());
        Future<Object> future;
        try{
            future = new AsyncResult(object);
            Long startTime = System.currentTimeMillis();
//            TimeUnit.SECONDS.sleep(6);
            //记录日志
            logMapper.insert(logPojo);
            Long endTime = System.currentTimeMillis();
            System.out.println("MsgServer send A 耗时：" + (endTime - startTime));
        }catch (Exception e){
            future = new AsyncResult("error");
            e.printStackTrace();
            return future;
        }
        return future;
    }

    //异步线程池写日志
    @Async("taskExecutor")
    public void addLog(LogPojo logPojo)  {
        System.out.println("MsgServer send A thread name->" + Thread.currentThread().getName());
        try{
            Long startTime = System.currentTimeMillis();
//            TimeUnit.SECONDS.sleep(6);
            //记录日志
            logMapper.insert(logPojo);
            Long endTime = System.currentTimeMillis();
            System.out.println("MsgServer send A 耗时：" + (endTime - startTime));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
