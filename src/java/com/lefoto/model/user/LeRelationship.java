/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.model.user;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * 用户好友关系类
 *
 * @author Eric
 */
@Entity
@Table(name = "le_relationship")
public class LeRelationship implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Column(name = "following_user_id", nullable = false)
    private int followingUserId;
    @Column(name = "group_id", nullable = false)
    private int groupId;
    //relation_type: 1表示为follow关系,2表示为互为好友关系
    @Column(name = "relation_type", nullable = false)
    private int relationType = 1;
    @Column(name = "create_user_id", nullable = false)
    private int createUserId;
    @Column(name = "create_time", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createTime = new Date();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFollowingUserId() {
        return followingUserId;
    }

    public void setFollowingUserId(int followingUserId) {
        this.followingUserId = followingUserId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getRelationType() {
        return relationType;
    }

    public void setRelationType(int relationType) {
        this.relationType = relationType;
    }

    public int getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
