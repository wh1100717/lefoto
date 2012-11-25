package com.lefoto.spider.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class SpiderFileUtil {

    public static void main(String[] args) throws Exception {
        deleteSmallSizeFile("D:/imgGrab/duitangdongman");
    }

    public static void deleteSmallSizeFile(String dirPath) {
        String desPath = dirPath + "1";
        File fileDir = new File(dirPath);
        File[] files = fileDir.listFiles();
        for (int index = 0; index < files.length; index++) {
            File file = files[index];
            String fileName = file.getName();
            String fileType = fileName.substring(fileName.indexOf(".") + 1);
            if (fileType.equals("gif")) {
                continue;
            }
            InputStream is;
            try {
                is = new FileInputStream(file.getAbsolutePath());
                try {
                    BufferedImage buff = ImageIO.read(is);

                    System.out.println(buff.getWidth());
                    int width = buff.getWidth();
                    is.close();
                    if (width < 300) {
                        Move(file, desPath);
                        System.out.println(index + file.getName()
                                + " finish moving");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("All Done");
    }

    public static boolean Move(File srcFile, String destPath) {
        // Destination directory
        File dir = new File(destPath);

        // Move file to new directory
        boolean success = srcFile.renameTo(new File(dir, srcFile.getName()));

        return success;
    }

    public static boolean Move(String srcFile, String destPath) {
        // File (or directory) to be moved
        File file = new File(srcFile);

        // Destination directory
        File dir = new File(destPath);

        // Move file to new directory
        boolean success = file.renameTo(new File(dir, file.getName()));

        return success;
    }

    public static void Copy(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) {
                InputStream inStream = new FileInputStream(oldPath);
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread;
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            System.out.println("error  ");
            e.printStackTrace();
        }
    }

    public static void Copy(File oldfile, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            // File oldfile = new File(oldPath);
            if (oldfile.exists()) {
                InputStream inStream = new FileInputStream(oldfile);
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread;
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            System.out.println("error  ");
            e.printStackTrace();
        }
    }
}
