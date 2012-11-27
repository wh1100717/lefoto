/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.common.popularize;

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

/**
 *
 * @author Eric
 */
public class photoPopulize {

    @Autowired
    PhotoService photoService;
    @Autowired
    UserService userService;

    public void PhotoCreation() throws FileNotFoundException, IOException, Exception {
        UserPopulize userPopulize = new UserPopulize();
        File fileDir = new File(Const.PHOTO_POPULIZE_PATH);
        File[] files = fileDir.listFiles();
        int index = 0;
        LeUser user = new LeUser();
        while (index < files.length) {
            if (index % 10 == 0) {
                String email = userPopulize.userCreate();
                user = userService.findUserByEmail(email);
            }
            File file = files[index];
            String upYunPath = UpYunUtil.upload(file);
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
        }
    }
}
