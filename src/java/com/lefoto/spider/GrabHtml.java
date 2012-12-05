/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.spider;

import com.lefoto.common.utils.JsonUtil;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.httpclient.HttpClient;

/**
 *
 * @author Eric
 */
public class GrabHtml {

    public static void main(String[] args) {
        grabDuiTangHTML();
    }
    
    	public static void grab91MeituHTML() {
		String urlStart = "http://www.91meitu.net/img-item/get-next?1&lastid=";
		URL url;
		String urlPath;
		try {
			for (int index = 1; index < 10000; index++) {
				urlPath = urlStart + String.valueOf(index) + "0";
				url = new URL(urlPath);
				HttpClient client = new HttpClient();

			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

    public static void grabWoXiHuanHTML() {
        String urlStart = "http://www.woxihuan.com/hot/catalog/cid/32376/name/%E5%85%A8%E9%83%A8?p=";
        String urlPath;
        URL url;
        try {
            for (int index = 1; index < 10000; index++) {
                urlPath = urlStart + String.valueOf(index);
                url = new URL(urlPath);
                URLConnection urlConn = url.openConnection(); // 打开网站链接s
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        urlConn.getInputStream())); // 实例化输入流，并获取网页代码
                String s; // 依次循环，至到读的值为空
                StringBuilder sb = new StringBuilder();
                while ((s = reader.readLine()) != null) {
                    sb.append(s);
                }
                reader.close();

                String result = sb.toString();

                StringBuilder input = new StringBuilder();
                input.append("123");
                String body;
                try {
                    body = (String) ((Map) JsonUtil.getMap4Json(result))
                            .get("body");

                    String regex = "(src='[^>]*.(gif|jpg)')";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher match = pattern.matcher(body);
                    while (match.find()) {
                        String imgUrl = match.group();
                        imgUrl = imgUrl.replace("196_", "680_");
                        input.append(imgUrl + "\n");
                        System.out.println(imgUrl);
                    }
                    RandomAccessFile randomFile = new RandomAccessFile("D:/imgGrab/imgUrls/dongman.txt",
                            "rw");
                    // 文件长度，字节数
                    long fileLength = randomFile.length();
                    // 将写文件指针移到文件尾。
                    randomFile.seek(fileLength);
                    randomFile.writeUTF(input.toString());
                    randomFile.close();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println(index + "Done");

            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Finished");
    }

    public static void grabDuiTangHTML() {

        String urlStart = "http://www.duitang.com/blogs/tags/hot/?page=";
        String urlEnd = "&tags=%E8%90%8C%E7%89%A9%2Cgif%2C%E5%AE%A0%E7%89%A9%2C%E5%B0%8F%E5%AD%A9%2C%E7%8C%AB&_type=";

        try {
            URL url;
            String realUrl;
            for (int index = 1; index < 10000; index++) {
                realUrl = urlStart + String.valueOf(index) + urlEnd;
                url = new URL(realUrl); // 将urlstr字符串网址实例化为URL定位地址s
                URLConnection urlConn = url.openConnection(); // 打开网站链接s
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(urlConn.getInputStream())); // 实例化输入流，并获取网页代码
                String s; // 依次循环，至到读的值为空
                StringBuilder sb = new StringBuilder();
                while ((s = reader.readLine()) != null) {
                    sb.append(s);
                }
                reader.close();

                String result = sb.toString();
                if (result == null || result.equals("") || result.equals("[]")
                        || result.equals("{}")) {
                    System.out.println("已无数据");
                    break;
                }
                List blogs = (List) ((Map) ((Map) JsonUtil.getMap4Json(result))
                        .get("data")).get("blogs");
                StringBuilder input = new StringBuilder();
                for (int j = 0; j < blogs.size(); j++) {
                    Map picInfo = (Map) blogs.get(j);
                    String picUrl = (String) picInfo.get("isrc");
                    picUrl = picUrl.replace(".thumb.200_0.", ".");
                    input.append(picUrl + "\n");
                    System.out.println(picUrl + "\n");

                }
                RandomAccessFile randomFile = new RandomAccessFile("D:/imgGrab/imgUrls/mengwu.txt",
                        "rw");
                // 文件长度，字节数
                long fileLength = randomFile.length();
                // 将写文件指针移到文件尾。
                randomFile.seek(fileLength);
                randomFile.writeUTF(input.toString());
                randomFile.close();

                System.out.println(index + " 另存为成功！\n");
            }

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
