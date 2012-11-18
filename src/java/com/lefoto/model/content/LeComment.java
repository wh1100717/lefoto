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
@Table(name = "le_comment")
public class LeComment implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    int id;
    @Column(name = "content")
    String content;
    @Column(name = "user_id")
    int userId;
    @Column(name = "user_name")
    String userName;
    @Column(name = "reply_comment_id")
    int replyCommentId;
    @Column(name = "reply_user_id")
    int replyUserId;
    @Column(name = "reply_user_name")
    String replyUserName;
    @Column(name = "topic_id")
    int topicId;
    @Column(name = "topic_user_id")
    int topicUserId;
    @Column(name = "topic_user_name")
    String rtopicUserName;
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

    public int getReplyCommentId() {
        return replyCommentId;
    }

    public void setReplyCommentId(int replyCommentId) {
        this.replyCommentId = replyCommentId;
    }

    public int getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(int replyUserId) {
        this.replyUserId = replyUserId;
    }

    public String getReplyUserName() {
        return replyUserName;
    }

    public void setReplyUserName(String replyUserName) {
        this.replyUserName = replyUserName;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getTopicUserId() {
        return topicUserId;
    }

    public void setTopicUserId(int topicUserId) {
        this.topicUserId = topicUserId;
    }

    public String getRtopicUserName() {
        return rtopicUserName;
    }

    public void setRtopicUserName(String rtopicUserName) {
        this.rtopicUserName = rtopicUserName;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
    
}
