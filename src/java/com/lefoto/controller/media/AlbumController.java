/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.controller.media;

import com.lefoto.common.base.BaseController;
import com.lefoto.common.base.Const;
import com.lefoto.model.media.LeAlbum;
import com.lefoto.model.user.LeUser;
import com.lefoto.service.iface.media.AlbumService;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Eric
 */
@Controller
@RequestMapping("/album")
public class AlbumController extends BaseController {

    @Autowired
    private AlbumService albumService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addAlbum(HttpServletRequest request) throws Exception {
        LeUser user = this.getRequestUser(request);
        String albumName = this.getParaStringFromRequest("albumName");
        String description = this.getParaStringFromRequest("description");
        int cateId = this.getParaIntFromRequest("cateId");
        LeAlbum album = albumService.findUserAlbumByName(albumName, user.getId());
        if (album != null) {
            return Const.FAILURE;
        }
        album = new LeAlbum();
        album.setName(albumName);
        album.setCategoryId(cateId);
        album.setUserId(user.getId());
        album.setUserName(user.getName());
        album.setDescription(description);
        album.setCreate_time(new Date());
        albumService.addAlbum(album);
        return Const.SUCCESS;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editAlbum(HttpServletRequest request) throws Exception {
        LeUser user = this.getRequestUser(request);
        String albumName = this.getParaStringFromRequest("albumName");
        String description = this.getParaStringFromRequest("description");
        LeAlbum album = albumService.findUserAlbumByName(albumName, user.getId());
        if (album == null) {
            return Const.FAILURE;
        }
        if (description != null) {
            album.setDescription(description);
        }
        if (albumName != null) {
            album.setName(albumName);
        }
        albumService.updateAlbum(album);
        return Const.SUCCESS;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteAlbum(HttpServletRequest request) throws Exception {
        LeUser user = this.getRequestUser(request);
        String albumName = this.getParaStringFromRequest("albumName");
        LeAlbum album = albumService.findUserAlbumByName(albumName, user.getId());
        if (album == null) {
            return Const.FAILURE;
        }
        albumService.deleteAlbum(album);
        return Const.SUCCESS;
    }
}
