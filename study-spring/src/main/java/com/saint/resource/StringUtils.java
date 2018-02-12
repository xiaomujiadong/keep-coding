package com.saint.resource;

import java.net.URL;

/**
 * Created by wdcao on 2017/11/21.
 */
public class StringUtils {

    /**
     * "file:/home/whf/cn/fh" -> "/home/whf/cn/fh"
     * "jar:file:/home/whf/foo.jar!cn/fh" -> "/home/whf/foo.jar"
     */
    public static String getRootPath(URL url) {
        String fileUrl = url.getFile();
        int pos = fileUrl.indexOf('!');

        if (-1 == pos) {
            return fileUrl;
        }

        return fileUrl.substring(5, pos);
    }

    /**
     * "cn.fh.lightning" -> "cn/fh/lightning"
     * @param name
     * @return
     */
    public static String dotToSplash(String name) {
        return name.replaceAll("\\.", "/");
    }

    /**
     * "Apple.class" -> "Apple"
     */
    public static String trimExtension(String name) {
        int pos = name.indexOf('.');
        if (-1 != pos) {
            return name.substring(0, pos);
        }

        return name;
    }

    /**
     * /application/home -> /home
     * @param uri
     * @return
     */
    public static String trimURI(String uri) {
        String trimmed = uri.substring(1);
        int splashIndex = trimmed.indexOf('/');

        return trimmed.substring(splashIndex);
    }

    public static String getNameByHump(String className){
        String beanName = className;
        if(className.contains(".")){
            String[] beanNames = className.split("\\.");
            System.out.println("beanName11: "+(beanNames.length));
            beanName = beanNames[beanNames.length-1];
            System.out.println("beanName: "+beanName);
        }

        System.out.println("beanName: "+beanName);
        String first = beanName.substring(0, 1);
        String last = beanName.substring(1);
        return first.toLowerCase()+last;
    }

    public static void main(String[] args){
        String className = "com.TerstTest";
        System.out.println(getNameByHump(className));
    }
}