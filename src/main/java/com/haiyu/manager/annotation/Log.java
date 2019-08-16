package com.haiyu.manager.annotation;

import java.lang.annotation.*;

/**
 * @ Author     : wzt.
 * @ Date       : Created in 10:33 2019/8/5
 * @ Description: 自定义日志注解，注解于方法上用于记录用户操作记录
 * @ Modified By:
 * @ Version    : 1.0$
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

    /** 业务模块 */
    String busi() default "";

    /**  操作类型 ：query-查询；add-新增；del-删除；update-修改*/
    int operType () default -1;

    /** 是否缓存：默认不缓存 */
    boolean cacheFlag () default false;

    /** 是否跳过权限验证：默认不跳过（true），跳过（false）*/
    boolean validate() default true;

}
