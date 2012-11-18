/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.service.impl.user;

import com.lefoto.dao.iface.user.UserDao;
import com.lefoto.model.user.LeUser;
import com.lefoto.model.user.LeUserInfo;
import com.lefoto.service.iface.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Eric
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void addUser(LeUser user) {
        this.userDao.addUser(user);
    }

    @Override
    public void delUser(LeUser user) {
        this.userDao.delUser(user);
    }

    @Override
    public void updateUser(LeUser user) {
        this.userDao.updateUser(user);
    }

    @Override
    public LeUser findUserByEmail(String email) {
        return this.userDao.findUserByEmail(email);
    }

    @Override
    public boolean checkUser(String email, String password) {
        return this.userDao.checkUser(email, password);
    }

    @Override
    public boolean checkEmailExist(String email) {
        return this.userDao.checkEmailExist(email);
    }

    @Override
    public void addOrUpdateUserInfo(LeUserInfo userInfo) {
        this.userDao.addOrUpdateUserInfo(userInfo);
    }
}
