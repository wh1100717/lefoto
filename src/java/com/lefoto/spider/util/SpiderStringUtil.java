package com.lefoto.spider.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

public class SpiderStringUtil {

    public static void main(String[] args) {
        duplcateProcess("D:/imgGrab/imgUrls/woxihuanMeiNv.txt");
    }

    public static void duplcateProcess(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        Map<String, Integer> url = new HashMap<String, Integer>();
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String imgUrl = null;
            int line = 1;
            while ((imgUrl = reader.readLine()) != null) {
                url.put(imgUrl, 1);
                line++;
            }
            reader.close();
            Object[] urls = url.keySet().toArray();
            StringBuilder sb = new StringBuilder();
            for (int index = 0; index < urls.length; index++) {
                sb.append(urls[index] + "\n");
            }
            RandomAccessFile randomFile = new RandomAccessFile(fileName+".du","rw");
            long fileLength = randomFile.length();
            randomFile.seek(fileLength);
            randomFile.writeUTF(sb.toString());
            randomFile.close();

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
        System.out.println("duplcateProcess Finish!");
    }
}
