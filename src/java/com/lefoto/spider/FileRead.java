package com.lefoto.spider;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileRead {
    // 根据url读取文件内容

    static int i = 0;

    public String readContent(String url) throws IOException,
            FileNotFoundException {
        try {
            String content = "";
            StringBuffer strBuffer = new StringBuffer();
            System.out.println("请求地址为：" + url);
            if (url.contains("flash")) {
                return "";
            }
            URL requestUrl = new URL(url);
            // 打开链接
            HttpURLConnection connection = (HttpURLConnection) requestUrl
                    .openConnection();
            connection.setRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            connection.connect();

            InputStream is = connection.getInputStream();
            while ((is.read()) != -1) {
                int all = is.available();
                byte[] b = new byte[all];
                is.read(b);
                strBuffer.append(new String(b, "UTF-8"));
            }
            if (is != null) {
                is.close();
            }
            content = strBuffer.toString();
            System.out.println("content" + content);
            return content;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
        }
    }

    // 获取指定格式的文件格式
    public List getUrlList(String content, String type) {
        List urlList = new ArrayList();
        Pattern pattern = Pattern.compile("http://.*." + type + "");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            String href = content.substring(start, end);
            System.out.println("url:" + href);
            urlList.add(href);
        }
        return urlList;
    }

    // 获取图片的链接
    public List getPhoto(String content, String type) {
        List list = new ArrayList();
        Pattern pattern = Pattern.compile("src=.http://.*." + type + "");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            String href = content.substring(start, end);
            if (href.contains("src=")) {
                href = href.substring(5);
            }

            System.out.println("url:" + href);
            list.add(href);
        }
        System.out.println("size++:" + list.size());
        return list;
    }

    public void writePhoto(List urlList) throws IOException,
            FileNotFoundException {
        for (int i = 0; i < urlList.size(); i++) {
            String url = (String) urlList.get(i);
            writePhotoToFile(url);
        }
    }

    public void writePhotoToFile(String url) throws IOException, FileNotFoundException {
        try {
            i++;
            String saveFileName = "D://test//images//";
            String today = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
            saveFileName += today + i;
            saveFileName += ".jpg";
            if (url.length() >= 55) {
                return;
            }
            URL requestUrl = new URL(url);
            DataInputStream dis = new DataInputStream(requestUrl.openStream());
            OutputStream fos = new FileOutputStream(new File(saveFileName));
            byte[] buff = new byte[1024];
            int len = -1;
            while ((len = dis.read(buff)) != -1) {
                fos.write(buff, 0, len);
            }
            buff = null;
            System.out.println("下载文件" + saveFileName + "完成");
            fos.close();
            dis.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return;
        }
    }
}
