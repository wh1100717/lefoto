/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.controller.backManage;

import com.imageGrab.utils.FileUtil;
import com.imageGrab.utils.StringUtil;
import com.lefoto.common.base.Const;
import com.lefoto.common.utils.PhotoUtil;
import com.lefoto.common.utils.UpYunUtil;
import com.lefoto.model.user.LeDefaultUserFace;
import com.lefoto.service.iface.user.UserService;
import java.awt.image.BufferedImage;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户管理模块
 * @author Eric
 */
@Controller
@RequestMapping("/back/user")
public class UserManageController {

    @Autowired
    UserService userService;

    /**
     * 添加默认用户头像
     * @param myfiles 获取的图片对象，其为数组，可实现多图上传
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws Exception
     */
    @RequestMapping(value = "/uploadDefaultUserFace", method = RequestMethod.POST)
    public @ResponseBody
    String uploadDefaultUserFace(@RequestParam("Filedata") MultipartFile[] myfiles, HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {

        for (MultipartFile myfile : myfiles) {
            if (myfile.isEmpty()) {
                System.out.println("文件未上传");
            } else {
             String type = myfile.getOriginalFilename().substring(myfile.getOriginalFilename().lastIndexOf(".") + 1);
                if (!PhotoUtil.checkPhotoType(type)) {
                    return Const.FAILURE;
                }
                String realPath = request.getSession().getServletContext().getRealPath(Const.UPLOAD_FOLDER_PATH);
                String destFileName = StringUtil.getRandomString(10) + "." + type;
                //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的  
                File destFile = new File(realPath, destFileName);
                FileUtils.copyInputStreamToFile(myfile.getInputStream(), destFile);
                BufferedImage bi = FileUtil.getImageInfo(destFile);
                //上传到UpYun
                String upYunPath = UpYunUtil.upload(destFile);
                //根据地址 获取相应的头像地址
                //TODO
                LeDefaultUserFace defaultUserFace = new LeDefaultUserFace();
                defaultUserFace.setUrl(upYunPath);
                userService.addDefaultUserFace(defaultUserFace);
            }
        }
        return Const.SUCCESS;
    }
}
