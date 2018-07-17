package com.hadoop.demo;

import java.io.File;
import java.net.URL;

import org.apache.commons.io.FileUtils;

/**
 * Created by wdcao on 2018/7/17.
 */
public class Test {
    /**
     * @param args
     */
    public static void main(String[] args) {

        String res = downloadFromUrl("http://download.bookset.me/2010/9/古典-拆掉思维里的墙-9787806638866.epub","d:/");
        System.out.println(res);
    }

    public static String downloadFromUrl(String url,String dir) {

        try {
            URL httpurl = new URL(url);
            String fileName = getFileNameFromUrl(url);
            System.out.println(fileName);
            File f = new File(dir + fileName);
            FileUtils.copyURLToFile(httpurl, f);
        } catch (Exception e) {
            e.printStackTrace();
            return "Fault!";
        }
        return "Successful!";
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
