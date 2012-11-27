/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.model.media;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * 用户对图片的顶和踩的记录表
 * @author Eric
 */
@Entity
@Table(name = "le_photo")
public class LePhotoUpdown {

    @Id
    @GeneratedValue
    @Column(name = "id")
    int id;
    @Column(name = "user_id")
    int userId;
    @Column(name = "photo_id")
    int photoId;
    //type ; 1表示顶，2表示踩
    @Column(name = "type")
    int type;
    @Column(name = "create_time")
    @Temporal(javax.persistence.TemporalType.DATE)
    Date create_time = new Date();

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

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}