/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.controller.backManage;

import com.lefoto.common.base.BaseController;
import com.lefoto.common.base.Const;
import com.lefoto.model.media.LeAlbum;
import com.lefoto.service.iface.media.AlbumService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Eric
 */
@Controller
@RequestMapping("/back/album")
public class AlbumManageController extends BaseController {

    @Autowired
    AlbumService albumService;

    @RequestMapping(value = "/add")
    public ModelAndView index(HttpServletRequest request) throws Exception {
        this.execute(request);
        ModelAndView mv = new ModelAndView("/back/categoryManage");
        String albumName = this.getParaStringFromRequest("albumName");
        String message = "";
        LeAlbum album = albumService.findUserAlbumByName(albumName, this.getUser().getId());
        if (album != null) {
            message = "已存在该相册";
            mv.addObject("message", message);
        } else {
            album = new LeAlbum();
            album.setName(albumName);
            album.setUserId(this.getUser().getId());
            album.setUserName(this.getUser().getNickName());
        }
        List<LeAlbum> albums = albumService.findAlbumsByUserId(this.getUser().getId());
        mv.addObject("categories", albums);
        return mv;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.execute(request);
        String albumName = this.getParaStringFromRequest("albumName");
        LeAlbum album = albumService.findUserAlbumByName(albumName, this.getUser().getId());
        if (album == null) {
            response.getWriter().write("该相册不存在或已被删除");
        } else {
            albumService.deleteAlbum(album);
            response.getWriter().write(Const.SUCCESS);
        }
    }
}
