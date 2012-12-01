/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.service.iface.media;

import com.lefoto.model.media.LePhoto;
import com.lefoto.model.media.LePhotoUpdown;
import java.util.List;

/**
 * 图片基本操作的Service类
 *
 * @author Eric
 */
public interface PhotoService {

    /**
     * 添加图片
     *
     * @param photo
     */
    public void addPhoto(LePhoto photo);
    
    /**
     * 用户转发|收藏照片
     * 
     * @param photoId
     * @param userId
     */
    public void forwardPhoto(int userId, int forward_user_id, int photoId, int cateId, int albumId);

    /**
     * 删除图片
     *
     * @param photo
     */
    public void deletePhoto(LePhoto photo);
    
    /**
     * 更新图片
     * 
     * @param photo
     */
    public void updatePhoto(LePhoto photo);

    /**
     * 根据id获取图片
     *
     * @param id
     * @return
     */
    public LePhoto findPhotoById(int id);

    /**
     * 根据相册id获取图片列表
     *
     * @param albumId
     * @return
     */
    public List<LePhoto> findPhotosByAlbumId(int albumId);

    /**
     * 根据用户id来获取图片列表
     *
     * @param userId
     * @return
     */
    public List<LePhoto> findPhotosByUserId(int userId);

    /**
     * 顶某一张图片
     *
     * @param photoId
     * @param userId
     */
    public void upPhoto(int photoId, int userId);

    /**
     * 踩某一张图片
     *
     * @param photoId
     * @param userId
     */
    public void downPhoto(int photoId, int userId);

    /**
     * 获取某个人对某张照片的顶踩情况
     *
     * @param photoId
     * @param userId
     * @return
     */
    public LePhotoUpdown findPhotoUpdown(int photoId, int userId);

    public List getPhotos(int cateId, int lastPhotoId, int size);
}
