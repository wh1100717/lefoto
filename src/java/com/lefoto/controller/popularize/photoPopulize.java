/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.controller.popularize;

import com.lefoto.common.base.Const;
import com.lefoto.common.utils.FileUtil;
import com.lefoto.common.utils.RandomUtil;
import com.lefoto.common.utils.UpYunUtil;
import com.lefoto.model.media.LePhoto;
import com.lefoto.model.user.LeUser;
import com.lefoto.service.iface.media.PhotoService;
import com.lefoto.service.iface.user.UserService;
import com.sun.media.jai.codec.SeekableStream;
import java.awt.color.CMMException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.media.jai.JAI;
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
        File fileDir = new File(Const.PHOTO_POPULIZE_PATH);
        String desPath = Const.PHOTO_POPULIZE_PATH + "1";
        File[] files = fileDir.listFiles();
        int index = 0;
        LeUser user;
        while (index < files.length) {
            user = userService.getRandomUser();
            System.out.println("获得随机用户");
//            if (index % 10 == 0) {
//                String email = userPopulize.userCreate(userService);
//                user = userService.findUserByEmail(email);
//                System.out.println("创建用户" + email);
//            }
            File file = files[index];
            int count = 0;
            String upYunPath = "";
            while (count < 5) {
                try {
                    upYunPath = UpYunUtil.upload(file);
                    count = 5;
                } catch (Exception e) {
                    System.err.println("上传至又拍云出错" + count + "次");
                    count++;
                }
            }
            if (upYunPath == null || upYunPath.equals("")) {
                System.err.println("上传又拍云出错");
                index++;
                continue;
            }
            System.out.println("上传图片" + index + " : " + file.getName());
            BufferedImage bufferedImage;
            try {
                // We try it with ImageIO
                bufferedImage =  ImageIO.read(file);
            } catch (CMMException ex) {
                // If we failed...
                // We reset the inputStream (start from the beginning)
                InputStream is = new FileInputStream(file);
                // And use JAI
                bufferedImage =  JAI.create("stream", SeekableStream.wrapInputStream(is, true)).getAsBufferedImage();
            }

            LePhoto photo = new LePhoto();
            photo.setCategoryId(2);
            photo.setName(file.getName());
            photo.setUrl(upYunPath);
            photo.setUserId(user.getId());
            photo.setUserName(user.getName());
            photo.setFileSize(file.length());
            photo.setHeight(bufferedImage.getHeight());
            photo.setWidth(bufferedImage.getWidth());

            //图片创建时间为近20天内的任意时刻随机 60*60*24*20 = 1728000
            photo.setCreateTime(new Date(System.currentTimeMillis() - 1000 * RandomUtil.getRandomNum(0, 1728000)));
            count = 0;
            while (count < 5) {
                try {
                    photoService.addPhoto(photo);
                    count = 5;
                } catch (Exception e) {
                    System.err.println("写入数据库出错" + count + "次");
                    count++;
                }
            }
            count = 0;
            while (count < 5) {
                try {
                    FileUtil.Move(file, desPath);
                    count = 5;
                } catch (Exception e) {
                    System.err.println("文件移动出错" + count + "次");
                    count++;
                }
            }
            index++;
        }
        System.out.println("Upload Done");
    }
}
