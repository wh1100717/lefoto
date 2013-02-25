/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.service.iface.media;

import com.lefoto.model.media.LeGrabRecord;
import com.lefoto.model.media.LePhoto;
import com.lefoto.model.media.LePhotoUp;
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
     * 喜欢某一张图片
     *
     * @param photoId
     * @param userId
     */
    public void upPhoto(int photoId, int userId);

    /**
     * 取消喜欢某一张图片
     *
     * @param photoId
     * @param userId
     */
    public void cancelUpPhoto(int photoId, int userId);

    /**
     * 获取某个人对某张照片的喜欢的情况
     *
     * @param photoId
     * @param userId
     * @return
     */
    public LePhotoUp findPhotoUp(int photoId, int userId);

    /**
     * 获取图片
     *
     * @param cateId 分类Id
     * @param lastPhotoId 异步Ajax请求过来最后一张图片的Id
     * @param size 要获取的记录数量
     * @param type
     * @return
     */
    public List getPhotos(int cateId, int lastPhotoId, int size);

    /**
     * 获取某一分类下所有的照片 用来进行后台缓存初始化
     *
     * @param cateId 分类Id
     * @param size 最大获取数据的数量
     * @return
     */
    public List getPhotosByAdmin(int cateId, int size, Integer[] types);

    /**
     * 根据PhotoId 找到跟这张照片有关的所有LePhotoUp记录
     *
     * @param photoId
     * @return
     */
    public List<LePhotoUp> findPhotoUps(int photoId);

    /**
     * 获取全部喜欢图片的记录
     *
     * @return
     */
    public List<LePhotoUp> getAllPhotoUps();

    public List<LePhoto> getGrabPhotosByAdmin(int size);

    public LeGrabRecord getGrabRecordByName(String name);

    public void setGrabRecordByName(String name, String value);

}
