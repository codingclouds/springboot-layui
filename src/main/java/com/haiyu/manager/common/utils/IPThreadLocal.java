package com.haiyu.manager.common.utils;

/**
 * @ Author     : wzt.
 * @ Date       : Created in 11:46 2019/8/13
 * @ Description: 存放Ip ThreadLocal类
 * @ Modified By:
 * @ Version    : 1.0$
 */
public class IPThreadLocal {

    private static final ThreadLocal<String> LOCAL = new ThreadLocal<String>();

    public static void set(String ip){
        LOCAL.set(ip);
    }

    public static String get(){
        return LOCAL.get();
    }

    public static void remove(){
        LOCAL.remove();
    }

    public IPThreadLocal() {
    }
}
