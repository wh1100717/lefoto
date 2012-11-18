/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.model.media;

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
@Table(name = "le_photo")
public class LePhoto implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    int id;
    @Column(name = "name")
    String name;
    @Column(name = "url")
    String url;
    @Column(name = "forward_photo_id")
    String forwardPhotoId;
    @Column(name = "topic_id")
    int topicId;
    @Column(name = "category_id")
    int categoryId;
    @Column(name = "album_id")
    int albumId;
    @Column(name = "file_size")
    int fileSize;
    @Column(name = "type")
    String type;
    @Column(name = "width")
    int width;
    @Column(name = "height")
    int height;
    @Column(name = "user_id")
    int userId;
    @Column(name = "user_name")
    String userName;
    @Column(name = "description")
    String description;
    @Column(name = "channel")
    int channel = 1;
    @Column(name = "create_time")
    @Temporal(javax.persistence.TemporalType.DATE)
    Date create_time = new Date();
    @Column(name = "is_delete")
    int isDelete = 0;

    public String getForwardPhotoId() {
        return forwardPhotoId;
    }

    public void setForwardPhotoId(String forwardPhotoId) {
        this.forwardPhotoId = forwardPhotoId;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTopicId() {
        return topicId;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
