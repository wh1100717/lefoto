/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.service.impl.media;

import com.lefoto.dao.iface.media.AlbumDao;
import com.lefoto.model.media.LeAlbum;
import com.lefoto.service.iface.media.AlbumService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Eric
 */
@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumDao albumDao;

    @Override
    public void addAlbum(LeAlbum album) {
        albumDao.addAlbum(album);
    }

    @Override
    public void deleteAlbum(LeAlbum album) {
        albumDao.deleteAlbum(album);
    }

    @Override
    public void updateAlbum(LeAlbum album) {
        albumDao.updateAlbum(album);
    }

    @Override
    public LeAlbum findAlbumById(int id) {
        return albumDao.findAlbumById(id);
    }

    @Override
    public List<LeAlbum> findAlbumsByUserId(int userId) {
        return albumDao.findAlbumsByUserId(userId);
    }

    @Override
    public LeAlbum findUserAlbumByName(String name, int userId) {
        return albumDao.findUserAlbumByName(name, userId);
    }
}
