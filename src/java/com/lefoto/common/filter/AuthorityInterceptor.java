/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.common.filter;

import com.lefoto.model.user.LeUser;
import com.lefoto.service.iface.user.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 用户权限验证模块
 *
 * @author Eric
 */
public class AuthorityInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    //Controller处理前执行
    public boolean preHandle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o) throws Exception {
        hsr.setAttribute("startTime", System.currentTimeMillis());
        //权限验证
        String email = (String) hsr.getSession().getAttribute("email");
        if (email != null) {
            LeUser user = this.userService.findUserByEmail(email);
            hsr.setAttribute("user", user);
        }
        return true;
    }

    //Controller处理后执行，可以获取Controller处理后的视图对象
    public void postHandle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, ModelAndView mav) throws Exception {
        long startTime = (Long) hsr.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        if (mav != null) {
            mav.addObject("handlingTime", startTime - endTime);
        }
    }
}
