/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.controller.backManage;

import com.lefoto.common.base.BaseController;
import com.lefoto.common.base.Const;
import com.lefoto.model.media.LeAlbum;
import com.lefoto.model.user.LeUser;
import com.lefoto.service.iface.media.AlbumService;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 相册管理Controller
 * @author Eric
 */
@Controller
@RequestMapping("/back/album")
public class AlbumManageController extends BaseController {

    @Autowired
    AlbumService albumService;

    /**
     * 添加相册
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add")
    public ModelAndView add(HttpServletRequest request) throws Exception {
        LeUser user = this.getRequestUser(request);
        ModelAndView mv = new ModelAndView("/back/albumManage");
        String albumName = this.getParaStringFromRequest("albumName");
        String message = "";
        if (albumName == null || albumName.equals("")) {
            message = "相册名不能为空";
            mv.addObject("message", message);
            List<LeAlbum> albums = albumService.findAlbumsByUserId(user.getId());
            mv.addObject("albums", albums);
            return mv;
        }
        LeAlbum album = albumService.findUserAlbumByName(albumName, user.getId());
        if (album != null) {
            message = "已存在该相册";
            mv.addObject("message", message);
        } else {
            album = new LeAlbum();
            album.setName(albumName);
            album.setUserId(user.getId());
            album.setUserName(user.getName());
            album.setCreate_time(new Date());
            albumService.addAlbum(album);
        }
        List<LeAlbum> albums = albumService.findAlbumsByUserId(user.getId());
        mv.addObject("albums", albums);
        return mv;
    }

    /**
     * 删除相册
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LeUser user = this.getRequestUser(request);
        String albumName = this.getParaStringFromRequest("albumName");
        LeAlbum album = albumService.findUserAlbumByName(albumName, user.getId());
        if (album == null) {
            response.getWriter().write("该相册不存在或已被删除");
        } else {
            albumService.deleteAlbum(album);
            response.getWriter().write(Const.SUCCESS);
        }
    }
}
