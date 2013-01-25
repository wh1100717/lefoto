/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.controller.popularize;

import com.lefoto.common.base.BaseController;
import com.lefoto.common.base.Const;
import com.lefoto.common.utils.FileUtil;
import com.lefoto.common.utils.UpYunUtil;
import com.lefoto.model.user.LeDefaultUserFace;
import com.lefoto.model.user.LeUser;
import com.lefoto.service.iface.user.UserService;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Eric
 */
@Controller
@RequestMapping(value = "/populize")
public class UserPopulize extends BaseController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/addUser")
    public @ResponseBody
    String userCreation() throws FileNotFoundException, IOException {
        int amount = this.getParaIntFromRequest("amount");
        amount = amount == -1 ? 1 : amount;
        for (int i = 0; i < amount; i++) {
            LeUser user = new LeUser();
            String userName = getUserName();
            user.setName(userName);
            String email = "";
            int index = 0;
            while (true) {
                if (index == 0) {
                    email = userName + "@lefoto.me";
                } else {
                    email = userName + String.valueOf(index) + "@lefoto.me";
                }
                if (userService.checkEmailExist(email)) {
                    index++;
                } else {
                    user.setEmail(email);
                    break;
                }
            }
            user.setFace(userService.findRandomDefaultUserFace().getUrl());
            user.setPassword(userName);
            Random random = new Random();
            boolean result = random.nextBoolean();
            if (result) {
                user.setSex(1);
            } else {
                user.setSex(0);
            }
            System.out.println(result);
            user.setSex(random.nextBoolean() == true ? 1 : 0);
            System.out.println(user.getSex());
            try {
                userService.addUser(user);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(i + " | " + user.getId() + " | " + user.getName());
        }
        return Const.SUCCESS;
    }

    @RequestMapping(value = "/addDefaultUserFace")
    public @ResponseBody
    String defaultUserFaceCreation() throws FileNotFoundException, IOException, Exception {
        File file = new File(Const.DEFAULT_USER_FACE_PATH);
        String desPath = Const.DEFAULT_USER_FACE_PATH + "1";
        File[] faces = file.listFiles();
        int count = 0;
        for (int index = 0; index < faces.length; index++) {
            File face = faces[index];
            count = 0;
            String facePath = "";
            while (count < 5) {
                try {
                    facePath = UpYunUtil.userFaceUpload(face);
                    count = 5;
                } catch (Exception e) {
                    count++;
                }
            }
            if (facePath == null || facePath.equals("")) {
                continue;
            }
            LeDefaultUserFace defaultUserFace = new LeDefaultUserFace();
            defaultUserFace.setUrl(facePath);
            count = 0;
            while (count < 5) {
                try {
                    userService.addDefaultUserFace(defaultUserFace);
                    FileUtil.Move(face, desPath);
                    count = 5;
                    System.out.println("Face No." + index + " : Done with name " + face.getName());
                } catch (Exception e) {
                    count++;
                }
            }
        }
        System.out.println("All Done");
        return Const.SUCCESS;
    }

    @RequestMapping(value = "/reallocUserFace")
    public @ResponseBody
    String reallocUserFace() throws FileNotFoundException, IOException, Exception {
        List<LeUser> userList = userService.findAllUsers();
        for (int index = 0; index < userList.size(); index++) {
            LeUser user = userList.get(index);
            if (user == null) {
                continue;
            }
            int count = 0;
            while (count < 5) {
                try {
                    user.setFace(userService.findRandomDefaultUserFace().getUrl());
                    userService.updateUser(user);
                    count = 5;
                    System.out.println("User No." + index + ":Done");
                } catch (Exception e) {
                    count++;
                }
            }
        }
        System.out.println("All Done");
        return Const.SUCCESS;
    }

    public String userCreate(UserService userService) throws FileNotFoundException, IOException {
        LeUser user = new LeUser();
        String userName = getUserName();
        user.setName(userName);
        String email = "";
        int index = 0;
        while (true) {
            if (index == 0) {
                email = userName + "@lefoto.me";
            } else {
                email = userName + String.valueOf(index) + "@lefoto.me";
            }
            if (userService.checkEmailExist(email)) {
                index++;
            } else {
                user.setEmail(email);
                break;
            }
        }
        user.setFace(userService.findRandomDefaultUserFace().getUrl());
        user.setPassword(userName);
        Random random = new Random();
        boolean result = random.nextBoolean();
        if (result) {
            user.setSex(1);
        } else {
            user.setSex(0);
        }
        System.out.println(result);
        user.setSex(random.nextBoolean() == true ? 1 : 0);
        System.out.println(user.getSex());
        userService.addUser(user);
        return email;
    }

//    @RequestMapping(value = "/addDefaultUserFace")
//    public String uploadDefaultUserFace() throws Exception {
//        String userFaceFolderPath = "D:/imgGrab/qqface";
//        File folder = new File(userFaceFolderPath);
//        File[] faces = folder.listFiles();
//        System.out.println("开始上传默认用户头像");
//        for (int index = 0; index < faces.length; index++) {
//            File face = faces[index];
//            System.out.println("头像名为：" + face.getName());
//
//            String facePath = UpYunUtil.userFaceUpload(face);
//            System.out.println("上传路径为：" + facePath);
//
//            LeDefaultUserFace userFace = new LeDefaultUserFace();
//            userFace.setUrl(facePath);
//            userService.addDefaultUserFace(userFace);
//        }
//        System.out.println("上传头像结束");
//        return Const.SUCCESS;
//    }
    private static String getUserName() throws FileNotFoundException, IOException {
        File file = new File(Const.DEFAULT_USER_NAME_PATH);
        InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");
        BufferedReader reader = new BufferedReader(read);
        String userName = null;
        Random random = new Random();
        int randomNum = random.nextInt(Const.DEFAULT_USER_NAME_LINES);
        int line = 1;
        // 一次读入一行，直到读入null为文件结束
        while ((userName = reader.readLine()) != null) {
            // 显示行号
            if (line == randomNum) {
                reader.close();
                return userName;
            }
            line++;
        }
        reader.close();
        return null;
    }
}
