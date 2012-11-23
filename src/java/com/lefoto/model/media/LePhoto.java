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
    String url = "";
    @Column(name = "forward_photo_id")
    int forwardPhotoId = 0;
    @Column(name = "category_id")
    int categoryId = 0;
    @Column(name = "album_id")
    int albumId = 0;
    @Column(name = "file_size")
    long fileSize = 0;
    @Column(name = "type")
    int type = 1;
    @Column(name = "width")
    int width = 0;
    @Column(name = "height")
    int height = 0;
    @Column(name = "user_id")
    int userId;
    @Column(name = "user_name")
    String userName;
    @Column(name = "description")
    String description = "";
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
    @Column(name = "is_delete")
    int isDelete = 0;

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

    public int getForwardPhotoId() {
        return forwardPhotoId;
    }

    public void setForwardPhotoId(int forwardPhotoId) {
        this.forwardPhotoId = forwardPhotoId;
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

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
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

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
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

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

}
