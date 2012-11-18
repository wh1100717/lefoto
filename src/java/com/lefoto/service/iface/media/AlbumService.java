/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.service.iface.media;

import com.lefoto.model.media.LeAlbum;

/**
 *
 * @author Eric
 */
public interface AlbumService {

    public void addAlbum(LeAlbum album);

    public void deleteAlbum(LeAlbum album);
    
    public void editAlbum(LeAlbum album);
}
