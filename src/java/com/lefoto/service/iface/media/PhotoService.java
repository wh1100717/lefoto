/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.service.iface.media;

import com.lefoto.model.media.LePhoto;

/**
 *
 * @author Eric
 */
public interface PhotoService {

    public void addPhoto(LePhoto photo);

    public void deletePhoto(LePhoto photo);

}
