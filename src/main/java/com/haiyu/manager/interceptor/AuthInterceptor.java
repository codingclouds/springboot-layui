package com.haiyu.manager.interceptor;

import com.haiyu.manager.annotation.Log;
import com.haiyu.manager.common.utils.IPThreadLocal;
import com.haiyu.manager.common.utils.IPUtils;
import com.haiyu.manager.controller.IndexController;
import com.haiyu.manager.dto.PermissionDTO;
import com.haiyu.manager.pojo.BaseAdminUser;
import com.haiyu.manager.service.AdminPermissionService;
import com.haiyu.manager.service.impl.AdminPermissionServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ Author     : wzt.
 * @ Date       : Created in 11:51 2019/8/5
 * @ Description: 用户请求过滤
 * @ Modified By:
 * @ Version    : 1.0$
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private AdminPermissionService permissionService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUrl=request.getRequestURI();
        IPThreadLocal.set(IPUtils.getIpAddr(request));
        if(handler.getClass().isAssignableFrom(HandlerMethod.class)){
            Log log = ((HandlerMethod) handler).getMethodAnnotation(Log.class);

            //没有声明需要权限,或者声明不验证权限
            if(log == null || log.validate() == false)
                return true;
            else{
//                //在这里实现权限验证逻辑
//                Map<String, Object> data = new HashMap<>();
//                BaseAdminUser user = (BaseAdminUser) SecurityUtils.getSubject().getPrincipal();
//                data = permissionService.getUserPerms(user);
//                List<PermissionDTO> permissionList =(List<PermissionDTO>) data.get("perm");
//                List<String> childPermissionList = new ArrayList<>();
//                if (!permissionList.isEmpty()){
//                    for (int i = 0; i < permissionList.size(); i++) {
//                        if (!permissionList.get(i).getChildrens().isEmpty()){
//                            for (int j = 0; j < permissionList.get(i).getChildrens().size(); j++) {
//                                childPermissionList.add(permissionList.get(i).getChildrens().get(j).getUrl());
//                            }
//                        }
//                    }
//                }
//
//                if (!childPermissionList.isEmpty()&&childPermissionList.contains(requestUrl)){
                    return true;
//                }else {
//                    //返回到登录界面
//                    response.sendRedirect("home");
//                    return false;
//                }

//                if(false)//如果验证成功返回true（这里直接写false来模拟验证失败的处理）
//                    return true;
//                else//如果验证失败
//                {
//                    //返回到登录界面
//                    response.sendRedirect("login");
//                    return false;
//                }
            }
        }else{
            return true;
        }
    }
}
