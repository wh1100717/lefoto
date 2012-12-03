/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.common.filter;

import com.lefoto.common.cache.UserCache;
import com.lefoto.service.iface.user.UserService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Eric
 */
public class LoadInfoServlet extends HttpServlet{
    
    static boolean isNotInit = false;
    
    @Autowired
    UserService userservice;
    
    @Override
    public void init() throws ServletException {
        System.out.println("内存初始化开始");
        System.out.println("用户数据初始化");
        UserCache.initUserMap(userservice);
        System.out.println("内存初始化结束");
        isNotInit = false;
    }
}
