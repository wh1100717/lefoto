/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Eric
 */
public class PhotoUtil {

    public static boolean checkPhotoType(String photoType) {
        Pattern pattern = Pattern.compile("^(jpg|jpeg|png|bmp|gif)$");
        Matcher matcher = pattern.matcher(photoType);
        boolean result = matcher.find();
        return result;
    }
}
