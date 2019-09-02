package com.haiyu.manager.aspect;


import com.google.gson.Gson;
import com.haiyu.manager.annotation.Log;
import com.haiyu.manager.common.utils.DateUtils;
import com.haiyu.manager.common.utils.IPThreadLocal;
import com.haiyu.manager.common.utils.ThreadPoolUtils;
import com.haiyu.manager.pojo.BaseAdminUser;
import com.haiyu.manager.pojo.LogPojo;
import com.haiyu.manager.service.AdminRoleService;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @ Author     : wzt.
 * @ Date       : Created in 11:15 2019/5/29
 * @ Description: 日志切面类
 * @ Modified By:
 * @ Version    : 1.0$
 */
@Order(2)
@Aspect
@Component("logAspect")
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
    @Autowired
    private ThreadPoolUtils threadPoolUtils;
    @Autowired
    private AdminRoleService adminRoleService;

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.haiyu.manager.annotation.Log)")
    public void logPointCut() {
    }

    /**
     * 方法执行前执行的方法
     */
    @Before(value = "logPointCut()")
    public void doBefore(){
    }

    @Around("@annotation(log)")
    public Object doInvoke(ProceedingJoinPoint pjp,Log log) {
        String startTime = DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss:SSS");
        Object result = null;
        BaseAdminUser user = (BaseAdminUser) SecurityUtils.getSubject().getPrincipal();
        LogPojo logPojo = new LogPojo();
        Gson gson = new Gson();
        try {
            if (log == null) {
                log = getAnnotationLog(pjp);
            }
            if (log.record()){
                logPojo.setBusi(log.busi());
                logPojo.setIp(IPThreadLocal.get()==null? null:IPThreadLocal.get());
                logPojo.setOperType(log.operType());
                logPojo.setMethod(pjp.getSignature().getName());
                logPojo.setRoleName(adminRoleService.getRole(user.getRoleId()).getRoleName());
                logPojo.setUserName(user.getSysUserName());
                logPojo.setStartTime(startTime);
                logPojo.setStatusCd(1);
                logPojo.setReqData(gson.toJson(pjp.getArgs()));
                //执行请求的方法
                result = pjp.proceed();

                logPojo.setRespData(gson.toJson(result));
                logPojo.setEndTime(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss:SSS"));

                threadPoolUtils.addLog(logPojo);
            }

        } catch (Throwable throwable) {
            throwable.printStackTrace();
            logger.error(throwable.getMessage(), throwable);
//            throw new RuntimeException(throwable);
            throwable.printStackTrace();

        } finally {
        }
        return result;
    }

    /**
     * 前置通知，拦截操作，方法返回后执行
     * @param joinPoint
     */
    @AfterReturning("@annotation(log)")
    public void doAfterReturn(JoinPoint joinPoint,Log log) {
//        handleLog(joinPoint, log,null);
    }

    /**
     * 拦截异常操作，有异常的时候执行
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value="logPointCut()",throwing = "e")
    public void doExpection(JoinPoint joinPoint,Throwable e){
        if(e != null){
            logger.error("***************拦截异常***************");
            logger.error("拦截出现异常{}"+e.toString());

        }
    }

    /**
     * 日志处理
     * @param joinPoint
     * @param
     */
    private void handleLog(JoinPoint joinPoint, Log log,Exception e) {
        try {
            if (log == null) {
                log = getAnnotationLog(joinPoint);
            }

            LogPojo logPojo = new LogPojo();
            logPojo.setBusi(log.busi());
            logPojo.setIp("127.0.0.1");
            logPojo.setOperType(log.operType());
            logPojo.setMethod(joinPoint.getSignature().getName());
            logPojo.setRoleName("");
            logPojo.setUserName("");
            logPojo.setStartTime("");
            logPojo.setEndTime("");
            logPojo.setStatusCd(1);

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

            threadPoolUtils.addLog(logPojo,new Object());

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

        private static Log getAnnotationLog1(ProceedingJoinPoint joinPoint) throws Exception {
            Signature signature = joinPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();
            if (method != null) {
                return method.getAnnotation(Log.class);
            }
            return null;
        }

}
