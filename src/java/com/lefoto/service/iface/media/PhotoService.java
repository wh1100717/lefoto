/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.service.iface.media;

import com.lefoto.model.media.LePhoto;
import java.util.List;

/**
 * 图片基本操作的Service
 * @author Eric
 */
public interface PhotoService {

    /**
     * 添加图片
     * @param photo
     */
    public void addPhoto(LePhoto photo);

    /**
     * 删除图片
     * @param photo
     */
    public void deletePhoto(LePhoto photo);
    
    /**
     * 根据id获取图片
     * @param id
     * @return
     */
    public LePhoto findPhotoById(int id);
    
    /**
     * 根据相册id获取图片列表
     * @param albumId
     * @return
     */
    public List<LePhoto> findPhotosByAlbumId(int albumId);
    
    /**
     * 根据用户id来获取图片列表
     * @param userId
     * @return
     */
    public List<LePhoto> findPhotosByUserId(int userId);
    
}
