package com.haiyu.manager.interceptor;

import com.haiyu.manager.annotation.Log;
import com.haiyu.manager.common.utils.IPThreadLocal;
import com.haiyu.manager.common.utils.IPUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ Author     : wzt.
 * @ Date       : Created in 11:51 2019/8/5
 * @ Description: 用户请求过滤
 * @ Modified By:
 * @ Version    : 1.0$
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        IPThreadLocal.set(IPUtils.getIpAddr(request));
        if(handler.getClass().isAssignableFrom(HandlerMethod.class)){
            Log log = ((HandlerMethod) handler).getMethodAnnotation(Log.class);

            //没有声明需要权限,或者声明不验证权限
            if(log == null || log.validate() == false)
                return true;
            else{
                //在这里实现权限验证逻辑
//                if(false)//如果验证成功返回true（这里直接写false来模拟验证失败的处理）
                    return true;
//                else//如果验证失败
//                {
//                    //返回到登录界面
//                    response.sendRedirect("login");
//                    return false;
//                }
            }
        }
        else
            return true;
    }
}
