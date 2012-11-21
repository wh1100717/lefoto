/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.controller.backManage;

import com.lefoto.common.base.BaseController;
import com.lefoto.common.base.Const;
import com.lefoto.common.utils.PhotoUtil;
import com.lefoto.common.utils.StringUtil;
import com.lefoto.common.utils.UpYunUtil;
import com.lefoto.model.media.LePhoto;
import com.lefoto.model.user.LeUser;
import com.lefoto.service.iface.media.PhotoService;
import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
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
public class UploadController extends BaseController {

    @Autowired
    private PhotoService photoService;
    private String uploadFolderPath = "/WEB-INF/upload";

    @RequestMapping(value = "/backManage/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("Filedata") MultipartFile[] myfiles, HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        //如果只是上传一个文件，则只需要MultipartFile类型接收文件即可，而且无需显式指定@RequestParam注解  
        //如果想上传多个文件，那么这里就要用MultipartFile[]类型来接收文件，并且还要指定@RequestParam注解  
        //并且上传多个文件时，前台表单中的所有<input type="file"/>的name都应该是myfiles，否则参数里的myfiles无法获取到所有上传的文件  

        LeUser user = this.getRequestUser(request);
        if (user == null) {
            return Const.FAILURE;
        }

        for (MultipartFile myfile : myfiles) {
            if (myfile.isEmpty()) {
                System.out.println("文件未上传");
            } else {
                String type = myfile.getOriginalFilename().substring(myfile.getOriginalFilename().lastIndexOf("."));
                if (!PhotoUtil.checkPhotoType(type)) {
                    return Const.FAILURE;
                }
                System.out.println("文件长度: " + myfile.getSize());
                System.out.println("文件类型: " + myfile.getContentType());
                System.out.println("文件名称: " + myfile.getName());
                System.out.println("文件原名: " + myfile.getOriginalFilename());
                System.out.println("========================================");
                //如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中  


                String realPath = request.getSession().getServletContext().getRealPath(uploadFolderPath);
                String destFileName = StringUtil.getRandomString(10) + type;
                //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的  
                File destFile = new File(realPath, destFileName);
                FileUtils.copyInputStreamToFile(myfile.getInputStream(), destFile);
                java.awt.image.BufferedImage bi = javax.imageio.ImageIO.read(destFile);
                System.out.println("图片宽度: " + bi.getWidth());
                System.out.println("图片高度: " + bi.getHeight());

                //上传到UpYun
                String upYunPath = UpYunUtil.upload(destFile);
                //插入Photo表
                LePhoto photo = new LePhoto();
                photo.setUrl(upYunPath);
//                photo.setAlbumId(albumId);
//                photo.setCategoryId(categoryId);
//                photo.setDescription(description);
                photo.setFileSize(myfile.getSize());
                photo.setHeight(bi.getHeight());
                photo.setName(myfile.getOriginalFilename());
                photo.setUserId(user.getId());
                photo.setUserName(user.getNickName());
                photoService.addPhoto(photo);
                //插入Photo表Done


                response.getWriter().write(destFileName);
            }
        }
        return Const.SUCCESS;
    }
}
