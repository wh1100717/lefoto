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
 * 图片类
 *
 * @author Eric
 */
@Entity
@Table(name = "le_photo")
public class LePhoto implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "url")
    private String url = "";
    @Column(name = "forward_photo_id")
    private int forwardPhotoId = 0;
    @Column(name = "forward_user_id")
    private int forwardUserId = 0;
    @Column(name = "category_id")
    private int categoryId = 0;
    @Column(name = "album_id")
    private int albumId = 0;
    @Column(name = "file_size")
    private long fileSize = 0;
    @Column(name = "type")
    private int type = 1;
    @Column(name = "width")
    private int width = 0;
    @Column(name = "height")
    private int height = 0;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "description")
    private String description = "";
    @Column(name = "comment_count")
    private int commentCount = 0;
    @Column(name = "forward_count")
    private int forwardCount = 0;
    @Column(name = "up_count")
    private int upCount = 0;
    @Column(name = "down_count")
    private int downCount = 0;
    @Column(name = "channel")
    private int channel = 1;
    @Column(name = "create_time")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createTime = new Date();
    @Column(name = "is_delete")
    private int isDelete = 0;

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

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public int getUpCount() {
        return upCount;
    }

    public void setUpCount(int upCount) {
        this.upCount = upCount;
    }

    public int getDownCount() {
        return downCount;
    }

    public int getForwardUserId() {
        return forwardUserId;
    }

    public void setForwardUserId(int forwardUserId) {
        this.forwardUserId = forwardUserId;
    }

    public void setDownCount(int downCount) {
        this.downCount = downCount;
    }

    public void addCommentCount() {
        this.commentCount = this.commentCount + 1;
    }

    public void addForwardCount() {
        this.forwardCount = this.forwardCount + 1;
    }

    public void addUpCount() {
        this.upCount = this.upCount + 1;
    }

    public void addDownCount() {
        this.downCount = this.downCount + 1;
    }
}
