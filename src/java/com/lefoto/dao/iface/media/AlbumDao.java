/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.dao.iface.media;

import com.lefoto.model.media.LeAlbum;
import java.util.List;

/**
 *
 * @author Eric
 */
public interface AlbumDao {

    public void addAlbum(LeAlbum album);

    public void deleteAlbum(LeAlbum album);

    public void updateAlbum(LeAlbum album);

    public LeAlbum findAlbumById(int id);

    public List<LeAlbum> findAlbumsByUserId(int userId);

    public LeAlbum findUserAlbumByName(String name, int userId);
}
