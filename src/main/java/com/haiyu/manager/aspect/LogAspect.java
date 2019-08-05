package com.haiyu.manager.aspect;


import com.haiyu.manager.annotation.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @ Author     : wzt.
 * @ Date       : Created in 11:15 2019/5/29
 * @ Description: 日志切面类
 * @ Modified By:
 * @ Version    : 1.0$
 */

@Aspect
@Component("logAspect")
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.qm.products.promt.annotation.Log)")
    public void logPointCut() {
    }

    /**
     * 方法执行前执行的方法
     */
//    @Before(value = "logPointCut()")
//    public void doBefore(){
//    }
    @Around("@annotation(log)")
    public Object doInvoke(ProceedingJoinPoint pjp,Log log) {
        long start = System.currentTimeMillis();

        Object result = null;

        try {
            result = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            logger.error(throwable.getMessage(), throwable);
            throw new RuntimeException(throwable);
        } finally {
            long end = System.currentTimeMillis();
            long elapsedTime = end - start;
            logger.info("时差为：{}",elapsedTime);
//            printLog(pjp, result, elapsedTime);
        }
        return result;
    }

    /**
     * 前置通知，拦截操作，方法返回后执行
     * @param joinPoint
     */
    @AfterReturning("@annotation(log)")
    public void doAfterReturn(JoinPoint joinPoint,Log log) {
        handleLog(joinPoint, log);
    }

    /**
     * 拦截异常操作，有异常的时候执行
     * @param joinPoint
     * @param e
     */
//    @AfterThrowing("@annotation(log)")
//    public void doExpection(JoinPoint joinPoint,Log log){
//        handleLog(joinPoint,log);
//    }

    /**
     * 日志处理
     * @param joinPoint
     * @param
     */
    private void handleLog(JoinPoint joinPoint, Log log) {
        try {

                // 获得注解
//                Log log = getAnnotationLog(joinPoint);
//                Log log = null;
                if (log == null) {
                    log = getAnnotationLog(joinPoint);
                }
                
                //获得类名
                String className = joinPoint.getTarget().getClass().getName();
                //获得方法名
                String methodName = joinPoint.getSignature().getName();
                //获得功能名
                String busi = log.busi();
                //获得业务模块名
                int operType = log.operType();
                //是否需要缓存
                boolean cacheFlag = log.cacheFlag();
                //是否跳过验证权限
                boolean validate = log.validate();

                //打印日志，如有需要还可以存入数据库
                logger.info(">>>>>>>>>>>>>业务模块：{}", busi);
                logger.info(">>>>>>>>>>>>>操作类型：{}", operType);
                logger.info(">>>>>>>>>>>>>是否缓存：{}", cacheFlag);
                logger.info(">>>>>>>>>>>>>是否跳过验证权限：{}", validate);
                logger.info(">>>>>>>>>>>>>类名：{}", className);
                logger.info(">>>>>>>>>>>>>方法名：{}", methodName);

        } catch (Exception exp) {
            // 记录本地异常日志
            logger.error("***************前置通知异常***************");
            logger.error("异常信息:{}", exp.toString());
            exp.printStackTrace();
        }
    }

        /**
         * 是否存在注解，如果存在就获取
         */
        private static Log getAnnotationLog(JoinPoint joinPoint) throws Exception {
            Signature signature = joinPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();
            if (method != null) {
                return method.getAnnotation(Log.class);
            }
            return null;
        }


}
