/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.dao.iface.media;

import com.lefoto.model.media.LePhoto;
import com.lefoto.model.media.LePhotoUp;
import java.util.List;

/**
 *
 * @author Eric
 */
public interface PhotoDao {

    public void addPhoto(LePhoto photo);

    public void deletePhoto(LePhoto photo);

    public void updatePhoto(LePhoto photo);

    public LePhoto findPhotoById(int id);

    public List<LePhoto> findPhotosByAlbumId(int albumId);

    public List<LePhoto> findPhotosByUserId(int userId);

    public void addPhotoUp(LePhotoUp photoUp);

    public void cancelUpPhoto(LePhotoUp photoUp);
    
    public LePhotoUp findPhotoUp(int photoId, int userId);

    public List getPhotos(int cateId, int lastPhotoId, int size);

    public List getPhotosByAdmin(int cateId, int size);

    public List<LePhotoUp> findPhotoUps(int photoId);
}
