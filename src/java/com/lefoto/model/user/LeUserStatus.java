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
 *
 * @author Eric
 */
@Entity
@Table(name = "le_user_status")
public class LeUserStatus implements Serializable {

    @Id
    @Column(name = "user_id")
    private int userId;
    @Column(name = "new_comment_count")
    private int newReplyCount;
    @Column(name = "new_up_count")
    private int newUpCount;
    @Column(name = "new_forward_count")
    private int newForwardCount;
    @Column(name = "new_at_count")
    private int newAtCount;
    @Column(name = "last_update_time")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastUpdateTime = new Date();

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNewReplyCount() {
        return newReplyCount;
    }

    public void setNewReplyCount(int newReplyCount) {
        this.newReplyCount = newReplyCount;
    }

    public int getNewUpCount() {
        return newUpCount;
    }

    public void setNewUpCount(int newUpCount) {
        this.newUpCount = newUpCount;
    }

    public int getNewForwardCount() {
        return newForwardCount;
    }

    public void setNewForwardCount(int newForwardCount) {
        this.newForwardCount = newForwardCount;
    }

    public int getNewAtCount() {
        return newAtCount;
    }

    public void setNewAtCount(int newAtCount) {
        this.newAtCount = newAtCount;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
