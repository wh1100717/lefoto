/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.service.iface.user;

import com.lefoto.model.user.LeDefaultUserFace;
import com.lefoto.model.user.LeUser;
import com.lefoto.model.user.LeUserInfo;
import java.util.List;

/**
 * 用户基本操作的Service类
 *
 * @author Eric
 */
public interface UserService {

    /**
     * 添加用户
     *
     * @param user
     */
    public void addUser(LeUser user);

    /**
     * 删除用户
     *
     * @param user
     */
    public void delUser(LeUser user);

    /**
     * 根据Email获取用户
     *
     * @param email
     * @return
     */
    public LeUser findUserByEmail(String email);

    /**
     * 获取所有用户
     *
     * @return
     */
    public List<LeUser> findAllUsers();

    /**
     * 获取随机用户
     *
     * @return
     */
    public LeUser getRandomUser();

    /**
     * 更新用户
     *
     * @param user
     */
    public void updateUser(LeUser user);

    /**
     * 更新用户头像
     *
     * @param userFace
     * @param userId
     */
    public void updateUserFace(String userFace, int userId);

    /**
     * 检查用户邮箱和密码是否正确
     *
     * @param email
     * @param password
     * @return
     */
    public boolean checkUser(String email, String password);

    /**
     * 检查邮箱是否存在
     *
     * @param email
     * @return
     */
    public boolean checkEmailExist(String email);

    /**
     * 添加或者更新用户信息
     *
     * @param userInfo
     */
    public void addOrUpdateUserInfo(LeUserInfo userInfo);

    /**
     * 添加默认用户头像
     *
     * @param defaultUserFace
     */
    public void addDefaultUserFace(LeDefaultUserFace defaultUserFace);

    /**
     * 根据Id获取用户默认头像
     *
     * @param id
     * @return
     */
    public LeDefaultUserFace findDefaultUserFaceById(int id);

    /**
     * 获取所有的用户头像
     *
     * @return
     */
    public List<LeDefaultUserFace> findAllDefaultUserFace();

    /**
     * 获取随机用户头像
     *
     * @return
     */
    public LeDefaultUserFace findRandomDefaultUserFace();
}
