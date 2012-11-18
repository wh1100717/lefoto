/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.service.impl.media;

import com.lefoto.dao.iface.media.PhotoDao;
import com.lefoto.model.media.LePhoto;
import com.lefoto.service.iface.media.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
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

}
