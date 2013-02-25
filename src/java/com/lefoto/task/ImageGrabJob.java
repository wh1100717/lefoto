/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.task;

import com.imageGrab.grab.ChunTuGrab;
import com.imageGrab.utils.FileUtil;
import com.lefoto.common.base.Const;
import com.lefoto.common.cache.SystemCache;
import com.lefoto.common.cache.UserCache;
import com.lefoto.common.utils.RandomUtil;
import com.lefoto.common.utils.UpYunUtil;
import com.lefoto.model.media.LePhoto;
import com.lefoto.model.user.LeUser;
import com.lefoto.service.iface.media.PhotoService;
import com.lefoto.service.iface.user.UserService;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author Eric
 */
@Component
public class ImageGrabJob {
    
    @Autowired
    PhotoService photoService;
    @Autowired
    UserService userService;
    
//    @Scheduled(cron = "0 2 12 * * ?")
    public void chuntuGrabJob() throws FileNotFoundException, IOException, Exception {
        System.out.println("Chuntu Image Grab Job Begin!");
//        Properties props = new Properties();
//        props.load(new BufferedInputStream(new FileInputStream(SystemCache.getCurrentSystemPath() + Const.IMAGE_GRAB_PROPERTIES_PATH)));

//        int lastTimeGrabMeiziId = Integer.parseInt(props.getProperty("chuntu.meizi.id"));
//        int lastTimeGrabMeinvId = Integer.parseInt(props.getProperty("chuntu.meinv.id"));
//        int lastTimeGrabTuwenId = Integer.parseInt(props.getProperty("chuntu.tuwen.id"));
//        int lastTimeGrabTaotuId = Integer.parseInt(props.getProperty("chuntu.taotu.id"));

        int lastTimeGrabMeiziId = Integer.parseInt(photoService.getGrabRecordByName("chuntu.meizi.id").getValue());
        int lastTimeGrabMeinvId = Integer.parseInt(photoService.getGrabRecordByName("chuntu.meinv.id").getValue());
        int lastTimeGrabTuwenId = Integer.parseInt(photoService.getGrabRecordByName("chuntu.tuwen.id").getValue());
        int lastTimeGrabTaotuId = Integer.parseInt(photoService.getGrabRecordByName("chuntu.taotu.id").getValue());
        
        boolean flag = true;

        //chuntuMeizi
        for (int index = 1; index < 100; index++) {
            try {
                if (!flag) {
                    break;
                }
                List<Map> meiziPhotos = ChunTuGrab.grabMeiziPhoto(index);
                for (int j = 0; j < meiziPhotos.size(); j++) {
                    Map photoMap = meiziPhotos.get(j);
                    int photoMapId = Integer.parseInt((String) photoMap.get("id"));
                    String photoUrl = (String) photoMap.get("imgUrl");
                    String description = (String) photoMap.get("description");
                    if (photoMapId <= lastTimeGrabMeiziId) {
                        flag = false;
                        break;
                    }
                    if (index == 1 && j == 0) {
                        photoService.setGrabRecordByName("chuntu.meizi.id", String.valueOf(photoMapId));
//                        props.setProperty("chuntu.meizi.id", String.valueOf(photoMapId));
                    }
                    populizePhoto(photoUrl, description);
                }
            } catch (IOException ex) {
                Logger.getLogger(ImageGrabJob.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(ImageGrabJob.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //chuntuMeinv
        flag = true;
        for (int index = 1; index < 100; index++) {
            try {
                if (!flag) {
                    break;
                }
                List<Map> meinvPhotos = ChunTuGrab.grabMeinvPhoto(index);
                for (int j = 0; j < meinvPhotos.size(); j++) {
                    Map photoMap = meinvPhotos.get(j);
                    int photoMapId = Integer.parseInt((String) photoMap.get("id"));
                    String photoUrl = (String) photoMap.get("imgUrl");
                    if (photoMapId <= lastTimeGrabMeinvId) {
                        flag = false;
                        break;
                    }
                    if (index == 1 && j == 0) {
                        photoService.setGrabRecordByName("chuntu.meinv.id", String.valueOf(photoMapId));
//                        props.setProperty("chuntu.meinv.id", String.valueOf(photoMapId));
                    }
                    populizePhoto(photoUrl, "");
                }
            } catch (IOException ex) {
                Logger.getLogger(ImageGrabJob.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(ImageGrabJob.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //chuntuTuwen
        flag = true;
        for (int index = 1; index < 100; index++) {
            try {
                if (!flag) {
                    break;
                }
                List<Map> tuwenPhotos = ChunTuGrab.grabTuwenPhoto(index);
                for (int j = 0; j < tuwenPhotos.size(); j++) {
                    Map photoMap = tuwenPhotos.get(j);
                    int photoMapId = Integer.parseInt((String) photoMap.get("id"));
                    String photoUrl = (String) photoMap.get("imgUrl");
                    String description = (String) photoMap.get("description");
                    if (photoMapId <= lastTimeGrabTuwenId) {
                        flag = false;
                        break;
                    }
                    if (index == 1 && j == 0) {
                        photoService.setGrabRecordByName("chuntu.tuwen.id", String.valueOf(photoMapId));
//                        props.setProperty("chuntu.tuwen.id", String.valueOf(photoMapId));
                    }
                    populizePhoto(photoUrl, description);
                }
            } catch (IOException ex) {
                Logger.getLogger(ImageGrabJob.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(ImageGrabJob.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //chuntuTaotu
        flag = true;
        for (int index = 1; index < 100; index++) {
            try {
                if (!flag) {
                    break;
                }
                List taotuIds = ChunTuGrab.grabTaoTuIds(index);
                for (int j = 0; j < taotuIds.size(); j++) {
                    int taotuId = Integer.parseInt((String) taotuIds.get(j));
                    if (taotuId <= lastTimeGrabTaotuId) {
                        flag = false;
                        break;
                    }
                    if (index == 1 && j == 0) {
                        photoService.setGrabRecordByName("chuntu.taotu.id", String.valueOf(taotuId));
//                        props.setProperty("chuntu.taotu.id", String.valueOf(taotuId));
                    }
                    List<String> photos = ChunTuGrab.grabTaoTuPhotosById(taotuId);
                    for (int k = 0; k < photos.size(); k++) {
                        String photoUrl = photos.get(k);
                        populizePhoto(photoUrl, "");
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ImageGrabJob.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

//    @Scheduled(cron = "0 0 1/7 * * ?")
    public void duitangGrabJob() {
        System.out.println("Duitang Image Grab Job Begin!");
    }

//    @Scheduled(cron = "0 0 2/8 * * ?")
    public void huabanGrabJob() {
        System.out.println("Huaban Image Grab Job Begin!");
    }

//    @Scheduled(cron = "0 0 3/9 * * ?")
    public void S91meituGrabJob() {
        System.out.println("91Meitu Image Grab Job Begin!");
    }

//    @Scheduled(cron = "0 0 4/10 * * ?")
    public void woxihuanGrabJob() {
        System.out.println("Woxihuan Image Grab Job Begin!");
    }
    
    public void populizePhoto(String imgUrl, String description) throws Exception {
        System.out.println("1:图片地址：" + imgUrl + " | 图片描述：" + description);
        String imgName = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);
        String downloadFilePath = SystemCache.getCurrentSystemPath() + Const.UPLOAD_FOLDER_PATH + "/" + imgName;
        System.out.println("2:图片保存在服务器的路径是：" + downloadFilePath);
        File photoFile = new File(downloadFilePath);
        FileUtil.download(imgUrl, photoFile.getAbsolutePath());
        if (!FileUtil.checkWidthLimit(photoFile.getAbsolutePath(), 400)) {
            return;
        }
        LeUser user = UserCache.getRandomRobootUser();
        if (user == null) {
            System.err.println("3:获取随机用户出错");
            return;
        }
        System.out.println("3:已获得随机用户");
        
        String upYunPath = "";
        int count = 1;
        while (count < 5) {
            try {
                upYunPath = UpYunUtil.upload(photoFile);
                count = 5;
            } catch (Exception e) {
                System.err.println("4:上传至又拍云出错" + count + "次");
                count++;
            }
        }
        if (upYunPath == null || upYunPath.equals("")) {
            System.err.println("4:上传又拍云出错");
        }
        System.out.println("4:上传图片" + " : " + photoFile.getName());
        BufferedImage bufferedImage = FileUtil.getImageInfo(photoFile);
        LePhoto photo = new LePhoto();
        photo.setName(photoFile.getName());
        photo.setUrl(upYunPath);
        photo.setUserId(user.getId());
        photo.setUserName(user.getName());
        photo.setFileSize(photoFile.length());
        photo.setHeight(bufferedImage.getHeight());
        photo.setWidth(bufferedImage.getWidth());
        photo.setDescription(description);
        photo.setType(3);
        photo.setCreateTime(new Date(System.currentTimeMillis() - 1000 * RandomUtil.getRandomNum(0, 1728000)));
        count = 1;
        while (count < 5) {
            try {
                photoService.addPhoto(photo);
                count = 5;
            } catch (Exception e) {
                System.err.println("5:写入数据库出错" + count + "次");
                count++;
            }
        }
        FileUtil.deleteFile(photoFile);
    }
}
