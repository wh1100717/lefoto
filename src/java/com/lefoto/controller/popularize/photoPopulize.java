/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.controller.popularize;

import com.lefoto.common.base.Const;
import com.lefoto.common.utils.UpYunUtil;
import com.lefoto.model.media.LePhoto;
import com.lefoto.model.user.LeUser;
import com.lefoto.service.iface.media.PhotoService;
import com.lefoto.service.iface.user.UserService;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Eric
 */
@Controller
@RequestMapping(value = "/photoPopulize")
public class photoPopulize {

    @Autowired
    PhotoService photoService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/addPhoto")
    public @ResponseBody
    void PhotoCreation() throws FileNotFoundException, IOException, Exception {
        UserPopulize userPopulize = new UserPopulize();
        File fileDir = new File(Const.PHOTO_POPULIZE_PATH);
        File[] files = fileDir.listFiles();
        int index = 0;
        LeUser user = new LeUser();
        System.out.println("开始上传默认用户头像");
        while (index < files.length) {
            if (index % 10 == 0) {
                String email = userPopulize.userCreate(userService);
                user = userService.findUserByEmail(email);
                System.out.println("创建用户" + email);
            }
            File file = files[index];
            String upYunPath = UpYunUtil.upload(file);
            System.out.println("上传图片" + file.getName());
            BufferedImage bufferedImage = ImageIO.read(file);
            LePhoto photo = new LePhoto();
            photo.setCategoryId(4);
            photo.setName(file.getName());
            photo.setUrl(upYunPath);
            photo.setUserId(user.getId());
            photo.setUserName(user.getName());
            photo.setFileSize(file.length());
            photo.setHeight(bufferedImage.getHeight());
            photo.setWidth(bufferedImage.getWidth());
            photoService.addPhoto(photo);
            index++;
        }
    }
}
