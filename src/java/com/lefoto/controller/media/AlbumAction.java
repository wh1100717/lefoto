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
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Eric
 */
@Controller
@RequestMapping("/album")
public class AlbumAction extends BaseController {

    @Autowired
    private AlbumService albumService;
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addAlbum(@RequestParam("albumName") String albumName, HttpServletRequest request){
        LeUser user = this.getRequestUser(request);
        LeAlbum album = albumService.findUserAlbumByName(albumName, user.getId());
        
        return Const.SUCCESS;
    }
}
