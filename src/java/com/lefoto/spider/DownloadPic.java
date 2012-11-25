package com.lefoto.spider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadPic {
//	static String fileName1 = "D:/imgGrab/imgUrls/woxihuanTongQu.txt";
//	static String filePath1 = "D:/imgGrab/woxihuanTongQu/";

    public static void main(String[] args) throws Exception {

        String[] fileNames = {"D:/imgGrab/imgUrls/woxihuanTongQu.txt",
            "D:/imgGrab/imgUrls/woxihuanMeiShi.txt",
            "D:/imgGrab/imgUrls/woxihuanMeiNv.txt",
            "D:/imgGrab/imgUrls/woxihuanGaoXiao.txt",
            "D:/imgGrab/imgUrls/woxihuanDongMan.txt",
            "D:/imgGrab/imgUrls/duitangMengTu.txt",
            "D:/imgGrab/imgUrls/duitangDongMan.txt"};
        String[] filePaths = {"D:/imgGrab/woxihuantongqu/",
            "D:/imgGrab/woxihuanmeishi/",
            "D:/imgGrab/woxihuanmeinv/",
            "D:/imgGrab/woxihuangaoxiao/",
            "D:/imgGrab/woxihuandongman/",
            "D:/imgGrab/duitangmengtu/",
            "D:/imgGrab/duitangdongman/"};
        for (int index = 0; index < 7; index++) {
            String fileName = fileNames[index];
            String filePath = filePaths[index];
            File file = new File(fileName);
            BufferedReader reader = null;
            try {
                System.out.println("以行为单位读取文件内容，一次读一整行：");
                reader = new BufferedReader(new FileReader(file));
                String imgUrl = null;
                int line = 1;
                // 一次读入一行，直到读入null为文件结束
                while ((imgUrl = reader.readLine()) != null) {
                    // 显示行号
                    String imgName = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);
                    System.out.println(imgName);
                    try {

                        download(imgUrl, filePath + imgName);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    line++;
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e1) {
                    }
                }
            }
        }
        System.out.println("Download Finish!：");
    }

    /**
     * 下载文件到本地
     *
     * @param urlString 被下载的文件地址
     * @param filename 本地文件名
     * @throws Exception 各种异常
     */
    public static void download(String urlString, String filename)
            throws Exception {
        // 构造URL
        URL url = new URL(urlString);
        // 打开连接
        URLConnection con = url.openConnection();
        // 输入流
        InputStream is = con.getInputStream();
        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        OutputStream os = new FileOutputStream(filename);
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
    }

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static void readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                System.out.println("line " + line + ": " + tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
}
