package com.saint.bookset;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.saint.util.IOUtils;
import org.apache.commons.io.FileUtils;

/**
 * 根据URL来下载
 * Created by wdcao on 2018/7/17.
 */
public class DownLoadUtil {
    /**
     * @param args
     */
    public static void main(String[] args) {

//        String res = downloadFromUrl("http://download.bookset.me/2010/9/古典-拆掉思维里的墙-9787806638866.epub","d:/");
//        System.out.println(res);
        //获取所有书的类别信息, key为每个类别的名称
        Map<String, Map<String, String>> bookTypeMap = CrawlBookType.getBookType();


        for(String key : bookTypeMap.keySet()){
            String bookTypeFilePath = Constant.DOWNLOAD_DIR+key+"\\downloadUrl.txt";
            List<String> bookUrlList = getBookDownLoadUrl(bookTypeFilePath);

            String bookFilePath = Constant.DOWNLOAD_DIR+key+"\\book\\";
            for(int i=0;i<bookUrlList.size(); i++){
                try {
                    downloadFromUrl(bookUrlList.get(i), bookFilePath);
                    Thread.sleep(30*1000);
                } catch (Exception e) {
                    e.printStackTrace();
                    --i;
                    try {
                        Thread.sleep(30*1000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    continue;
                }
            }
        }

    }

    public static List<String> getBookDownLoadUrl(String bookTypeFilePath) {

        String content = IOUtils.readToString(bookTypeFilePath);

        String[] contentArray = content.split("\r\n");
        List<String> urlList = new ArrayList<String>();
        for(String s:contentArray){
            String url = s.split(", ")[1].replace("书URL：", "");

            urlList.add(url);
        }
        return urlList;
    }

    public static void downloadFromUrl(String url,String dir) throws IOException {
        URL httpurl = new URL(url);
        String fileName = URLDecoder.decode(getFileNameFromUrl(url));
        System.out.println(fileName);
        File f = new File(dir + fileName);
        FileUtils.copyURLToFile(httpurl, f);
    }

    public static String getFileNameFromUrl(String url){
        String name = new Long(System.currentTimeMillis()).toString() + ".X";
        int index = url.lastIndexOf("/");
        if(index > 0){
            name = url.substring(index + 1);
            if(name.trim().length()>0){
                return name;
            }
        }
        return name;
    }
}
