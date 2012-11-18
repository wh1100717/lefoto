/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.model.content;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Eric
 */
@Entity
@Table(name = "le_topic")
public class LeTopic implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    int id;
    @Column(name = "content")
    String content;
    @Column(name = "object_id")
    int objectId;
    @Column(name = "objcet_type")
    int objectType = 1;
    @Column(name = "root_id")
    int rootId;
    @Column(name = "user_id")
    int userId;
    @Column(name = "user_name")
    String userName;
    @Column(name = "comment_count")
    int commentCount = 0;
    @Column(name = "forward_count")
    int forwardCount = 0;
    @Column(name = "favorite_count")
    int favoriteCount = 0;
    @Column(name = "channel")
    int channel = 1;
    @Column(name = "create_time")
    @Temporal(javax.persistence.TemporalType.DATE)
    Date create_time = new Date();

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

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public int getObjectType() {
        return objectType;
    }

    public void setObjectType(int objectType) {
        this.objectType = objectType;
    }

    public int getRootId() {
        return rootId;
    }

    public void setRootId(int rootId) {
        this.rootId = rootId;
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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getForwardCount() {
        return forwardCount;
    }

    public void setForwardCount(int forwardCount) {
        this.forwardCount = forwardCount;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
