/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.common.utils;

import java.io.File;

/**
 *
 * @author Eric
 */
public class FileUtil {

    public static boolean Move(File srcFile, String destPath) {
        // Destination directory
        File dir = new File(destPath);

        // Move file to new directory
        boolean success = srcFile.renameTo(new File(dir, srcFile.getName()));

        return success;
    }
}
