/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.common.filter;

import com.lefoto.common.cache.PhotoCache;
import com.lefoto.common.cache.UserCache;
import com.lefoto.service.iface.media.PhotoService;
import com.lefoto.service.iface.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Eric
 */
@Component
public class LoadInfoServlet {

    static boolean isNotInit = true;
    @Autowired
    private UserService userService;
    @Autowired
    private PhotoService photoService;

    public void init() {
        System.out.println("内存初始化开始");
        System.out.println("用户数据初始化");
        UserCache.initUserMap(userService);
        System.out.println("照片初始化开始");
        PhotoCache.initPhotoList(photoService);
        System.out.println("内存初始化结束");

        isNotInit = false;
    }
}
