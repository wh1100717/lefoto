/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.controller.media;

import com.lefoto.model.ExtJSFormResult;
import com.lefoto.model.FileUploadBean;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Eric
 */
@Controller
@RequestMapping(value = "/media/upload")
public class UploadAction {

    private String uploadFolderPath = "/tmp/";

   /**
     * 上传路径
     */

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    String create(FileUploadBean uploadItem, BindingResult result) throws IOException {

        ExtJSFormResult extjsFormResult = new ExtJSFormResult();

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.err.println("Error: " + error.getCode() + " - "
                        + error.getDefaultMessage());
            }

            // 设置ExtJS返回 - error
            extjsFormResult.setSuccess(false);

            return extjsFormResult.toString();
        }

        // Some type of file processing...
        MultipartFile file = uploadItem.getFile();
        String fileName = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        if(file.getSize()>0){
        inputStream = file.getInputStream();
        if(file.getSize()>100000){
             System.out.println("File Size:::" + file.getSize());
             extjsFormResult.setSuccess(false);
            return extjsFormResult.toString();
        }
        System.out.println("size::" + file.getSize());
           
            File newFile = new File(uploadFolderPath + "/images/");
            if(!newFile.exists()){
              newFile.mkdirs();
            }
            fileName = uploadFolderPath + "/images/" + file.getOriginalFilename();
           
            outputStream = new FileOutputStream(fileName);
            System.out.println("fileName:" + file.getOriginalFilename());

            int readBytes = 0;
            byte[] buffer = new byte[10000];
            while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
                    outputStream.write(buffer, 0, readBytes);
            }
            outputStream.close();
            inputStream.close();
        }

        // 设置ExtJS返回 - sucsess
        extjsFormResult.setSuccess(true);

        return extjsFormResult.toString();
    }
}
