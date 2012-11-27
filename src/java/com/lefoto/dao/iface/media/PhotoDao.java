/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.dao.iface.media;

import com.lefoto.model.media.LePhoto;
import com.lefoto.model.media.LePhotoUpdown;
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

    public void upPhoto(int photoId, int userId);

    public void downPhoto(int photoId, int userId);

    public LePhotoUpdown findPhotoUpdown(int photoId, int userId);
}
