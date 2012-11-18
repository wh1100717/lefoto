/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.model.user;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * 用户扩展信息
 * @author Eric
 */
@Entity
@Table(name = "user_info")
public class LeUserInfo implements Serializable {

    @Id
    @Column(name = "user_id")
    int userId;
    
    @Column(name = "age")
    int age;
    
    @Column(name = "birthday")
    @Temporal(javax.persistence.TemporalType.DATE)
    Date birthday;
    
    @Column(name = "mobile")
    int mobile;
    
    @Column(name = "address")
    String address;
    
    @Column(name = "qq")
    int qq;
    
    @Column(name = "sina_weibo")
    String sinaWeibo;
    
    @Column(name = "renren")
    String renren;
    
    @Column(name = "last_update_time")
    @Temporal(javax.persistence.TemporalType.DATE)
    Date lastUpdateTime = new Date();
    
    @Column(name = "is_delete")
    int isDelete = 0;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getQq() {
        return qq;
    }

    public void setQq(int qq) {
        this.qq = qq;
    }

    public String getSinaWeibo() {
        return sinaWeibo;
    }

    public void setSinaWeibo(String sinaWeibo) {
        this.sinaWeibo = sinaWeibo;
    }

    public String getRenren() {
        return renren;
    }

    public void setRenren(String renren) {
        this.renren = renren;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

}
