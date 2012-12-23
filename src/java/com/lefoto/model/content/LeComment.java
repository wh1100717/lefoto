/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.model.content;

import com.lefoto.common.cache.UserCache;
import com.lefoto.model.user.LeUser;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * 评论类
 *
 * @author Eric
 */
@Entity
@Table(name = "le_comment")
public class LeComment implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "content")
    private String content;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "user_name")
    private String userName;
    //objecType表示评论的对象类型，1表示为图片
    @Column(name = "object_type")
    private int objectType;
    @Column(name = "object_id")
    private int objectId;
    @Column(name = "object_user_id")
    private int objectUserId;
    @Column(name = "channel")
    private int channel = 1;
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime = new Date();
    
    @Transient
    private String userFace;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserFace() {
        LeUser user = UserCache.getUserById(this.userId);
        if (user != null) {
            userFace = user.getFace();
            return userFace;
        } else {
            return userFace;
        }
    }

    public void setUserFace(String userFace) {
        this.userFace = userFace;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getObjectType() {
        return objectType;
    }

    public void setObjectType(int objectType) {
        this.objectType = objectType;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public int getObjectUserId() {
        return objectUserId;
    }

    public void setObjectUserId(int objectUserId) {
        this.objectUserId = objectUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
