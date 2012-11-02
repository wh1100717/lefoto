/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.user.model;

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
@Table(name = "le_relationship")
public class Relationship implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    int id;
    
    @Column(name = "user_id", nullable = false)
    int userId;
    
    @Column(name = "user_name", nullable = false)
    String userName;
    
    @Column(name = "follow_user_id", nullable = false)
    int followUserId;
    
    @Column(name = "follow_user_name", nullable = false)
    String followUserName;
    
    @Column(name = "group_id", nullable = false)
    int groupId;
    
    @Column(name = "group_name", nullable = false)
    String groupName;
    
    @Column(name = "relation_tyoe", nullable = false)
    int relationType = 1;
    
    @Column(name = "create_user_id", nullable = false)
    int createUserId;
    
    @Column(name = "create_time", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    Date createTime = new Date();

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getFollowUserId() {
        return followUserId;
    }

    public void setFollowUserId(int followUserId) {
        this.followUserId = followUserId;
    }

    public String getFollowUserName() {
        return followUserName;
    }

    public void setFollowUserName(String followUserName) {
        this.followUserName = followUserName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
