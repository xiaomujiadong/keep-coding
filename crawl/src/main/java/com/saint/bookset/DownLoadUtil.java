package com.saint.bookset;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Timestamp;
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

        int bookCount = 1;

        for(String key : bookTypeMap.keySet()){
            String bookTypeFilePath = Constant.DOWNLOAD_DIR+key+"\\downloadUrl.txt";
            List<String> bookUrlList = getBookDownLoadUrl(bookTypeFilePath);

            String bookFilePath = Constant.DOWNLOAD_DIR+key+"\\book\\";
            for(int i=0;i<bookUrlList.size(); i++){
                try {

                    boolean result = downloadFromUrl(bookUrlList.get(i), bookFilePath, bookCount);

                    if(result){
                        Thread.sleep(30*1000);
                    }
                    ++bookCount;
                }catch (FileNotFoundException ex){
                    System.out.print("FileNotFoundException 下载文件异常发生时间： "+new Timestamp(System.currentTimeMillis()));
                    ex.printStackTrace();
                    try {
                        Thread.sleep(30*1000);
                    } catch (InterruptedException e1) {
                        System.out.print("FileNotFoundException 线程休眠异常发生时间： "+new Timestamp(System.currentTimeMillis()));
                        e1.printStackTrace();
                    }
                    continue;
                } catch (Exception e) {
                    System.out.print("Exception 下载文件异常发生时间： "+new Timestamp(System.currentTimeMillis()));
                    e.printStackTrace();
                    --i;
                    try {
                        Thread.sleep(30*1000);
                    } catch (InterruptedException e1) {
                        System.out.print("Exception 线程休眠异常发生时间： "+new Timestamp(System.currentTimeMillis()));
                        e1.printStackTrace();
                    }
                    continue;
                }
            }
        }

//        String str = "D:\\bookset\\小说\\book\\金醉-北洋夜行记-9787535495532.mobi";
//        File dir = new File(str);
//        if(dir.exists()) System.out.println("The file exist");
//        else System.out.println("The file is not exist");

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

    public static boolean downloadFromUrl(String url,String dir, int bookCount) throws FileNotFoundException,IOException {
        URL httpurl = new URL(url);
        String fileName = URLDecoder.decode(getFileNameFromUrl(url));

        File f = new File(dir + fileName);
        if(f.exists()) {
            System.out.println(new Timestamp(System.currentTimeMillis())+": 当前路径: "+dir+" : 书："+fileName+" 已经存在, 总共下载： "+bookCount+" 本书");
            return false;
        }else{
            System.out.println("文件路径： "+dir + fileName+", 总共下载： "+bookCount+" 本书");
            FileUtils.copyURLToFile(httpurl, f);
        }
        return true;
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
