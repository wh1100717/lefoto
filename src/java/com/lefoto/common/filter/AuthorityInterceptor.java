/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.common.filter;

import com.lefoto.common.base.Const;
import com.lefoto.model.user.LeUser;
import com.lefoto.service.iface.user.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 用户权限验证模块
 *
 * @author Eric
 */
public class AuthorityInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;
    
    @Autowired
    private LoadInfoServlet loadInfoServlet;

    //Controller处理前执行
    @Override
    public boolean preHandle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o) throws Exception {
        
        if(LoadInfoServlet.isNotInit){
            loadInfoServlet.init(hsr);
        }
        
        hsr.setAttribute("RESOURCE_DIR", Const.RESOURCE_DIR);
//        hsr.setAttribute("startTime", System.currentTimeMillis());
        //权限验证
        String email = (String) hsr.getSession().getAttribute("email");
        //临时测试的时候 添加管理员用户
//        email = "admin@lefoto.me";
        //Done
        if (email != null) {
            LeUser user = this.userService.findUserByEmail(email);
            hsr.setAttribute("user", user);
        }
        return true;
    }

    //Controller处理后执行，可以获取Controller处理后的视图对象
//    @Override
//    public void postHandle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, ModelAndView mav) throws Exception {
//        long startTime = (Long) hsr.getAttribute("startTime");
//        long endTime = System.currentTimeMillis();
//        if (mav != null) {
//            mav.addObject("handlingTime", startTime - endTime);
//        }
//    }
}
