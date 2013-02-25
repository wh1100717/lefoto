/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.service.impl.media;

import com.lefoto.common.cache.PhotoCache;
import com.lefoto.dao.iface.media.AlbumDao;
import com.lefoto.dao.iface.media.PhotoDao;
import com.lefoto.dao.iface.user.UserDao;
import com.lefoto.model.media.LeAlbum;
import com.lefoto.model.media.LeGrabRecord;
import com.lefoto.model.media.LePhoto;
import com.lefoto.model.media.LePhotoUp;
import com.lefoto.model.user.LeUserStatus;
import com.lefoto.service.iface.media.PhotoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 图片Service的实现类
 *
 * @author Eric
 */
@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoDao photoDao;
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private UserDao userDao;

    @Override
    public void addPhoto(LePhoto photo) {
        int albumId = photo.getAlbumId();
        LeAlbum album;
        if (albumId == 0) {
            album = albumDao.findUserAlbumByName("默认相册", photo.getUserId());
            if (album == null) {
                album = new LeAlbum();
                album.setName("默认相册");
                album.setUserId(photo.getUserId());
                album.setUserName(photo.getUserName());
                albumDao.addAlbum(album);
            }
            photo.setAlbumId(album.getId());
        }
        photoDao.addPhoto(photo);
    }

    @Override
    public void forwardPhoto(int userId, int forward_user_id, int photoId, int cateId, int albumId) {
        LePhoto forwardPhoto = new LePhoto();
        LePhoto photo = this.findPhotoById(photoId);
        forwardPhoto.setAlbumId(albumId);
        forwardPhoto.setCategoryId(cateId);
        forwardPhoto.setFileSize(photo.getFileSize());
        forwardPhoto.setHeight(photo.getHeight());
        forwardPhoto.setWidth(photo.getWidth());
        forwardPhoto.setType(photo.getType());
        forwardPhoto.setUrl(photo.getUrl());
        forwardPhoto.setForwardPhotoId(photoId);
        forwardPhoto.setForwardUserId(forward_user_id);
        photoDao.addPhoto(forwardPhoto);
        photo.setForwardCount(photo.getForwardCount() + 1);
        photoDao.updatePhoto(photo);

        LeUserStatus userStatus = userDao.findUserStatus(forward_user_id);
        if (userStatus != null) {
            userStatus.setNewForwardCount(userStatus.getNewForwardCount() + 1);
            userDao.updateUserStatus(userStatus);
        }
    }

    @Override
    public void deletePhoto(LePhoto photo) {
        photoDao.deletePhoto(photo);
    }

    @Override
    public void updatePhoto(LePhoto photo) {
        photoDao.updatePhoto(photo);
    }

    @Override
    public void upPhoto(int photoId, int userId) {
        LePhotoUp photoUp = this.findPhotoUp(photoId, userId);
        if (photoUp == null) {
            photoUp = new LePhotoUp();
            photoUp.setPhotoId(photoId);
            photoUp.setUserId(userId);
            photoDao.addPhotoUp(photoUp);
            LePhoto photo = photoDao.findPhotoById(photoId);
            if (photo != null) {
                LeUserStatus userStatus = userDao.findUserStatus(photo.getUserId());
                if (userStatus != null) {
                    userStatus.setNewForwardCount(userStatus.getNewForwardCount() + 1);
                    userDao.updateUserStatus(userStatus);
                }
            }
        }
    }

    @Override
    public void cancelUpPhoto(int photoId, int userId) {
        LePhotoUp photoUp = this.findPhotoUp(photoId, userId);
        if (photoUp != null) {
            photoDao.cancelUpPhoto(photoUp);
            LePhoto photo = photoDao.findPhotoById(photoId);
            LeUserStatus userStatus = userDao.findUserStatus(photo.getUserId());
            if (userStatus != null) {
                userStatus.setNewForwardCount(userStatus.getNewForwardCount() - 1);
                userDao.updateUserStatus(userStatus);
            }

        }
    }

    @Override
    public LePhoto findPhotoById(int id) {
        return photoDao.findPhotoById(id);
    }

    @Override
    public List<LePhoto> findPhotosByAlbumId(int albumId) {
        return photoDao.findPhotosByAlbumId(albumId);
    }

    @Override
    public List<LePhoto> findPhotosByUserId(int userId) {
        return photoDao.findPhotosByUserId(userId);
    }

    @Override
    public LePhotoUp findPhotoUp(int photoId, int userId) {
        return photoDao.findPhotoUp(photoId, userId);
    }

    @Override
    public List getPhotos(int cateId, int lastPhotoId, int size) {
        return photoDao.getPhotos(cateId, lastPhotoId, size);
    }

    @Override
    public List getPhotosByAdmin(int cateId, int size, Integer[] types) {
        return photoDao.getPhotosByAdmin(cateId, size, types);
    }

    @Override
    public List<LePhotoUp> findPhotoUps(int photoId) {
        return photoDao.findPhotoUps(photoId);
    }

    @Override
    public List<LePhotoUp> getAllPhotoUps() {
        return photoDao.getAllPhotoUps();
    }

    @Override
    public List<LePhoto> getGrabPhotosByAdmin(int size) {
        return photoDao.getGrabPhotosByAdmin(size);
    }

    @Override
    public LeGrabRecord getGrabRecordByName(String name) {
        return photoDao.getGrabRecordByName(name);
    }

    @Override
    public void setGrabRecordByName(String name, String value) {
        photoDao.setGrabRecordByName(name, value);
    }
}
