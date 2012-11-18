/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.service.impl.media;

import com.lefoto.dao.iface.media.PhotoDao;
import com.lefoto.model.media.LePhoto;
import com.lefoto.service.iface.media.PhotoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 图片Service的实现类
 * @author Eric
 */
@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoDao photoDao;

    @Override
    public void addPhoto(LePhoto photo) {
        photoDao.addPhoto(photo);
    }

    @Override
    public void deletePhoto(LePhoto photo) {
        photoDao.deletePhoto(photo);
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

}
