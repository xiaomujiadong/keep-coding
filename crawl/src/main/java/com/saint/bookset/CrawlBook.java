package com.saint.bookset;

import com.saint.util.HttpUtil;
import com.saint.util.IOUtils;
import com.saint.util.MatcherUtil;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wdcao on 2018/7/18.
 */
public class CrawlBook {
    public static void main(String[] args){

//        String url = "<A class=\"mbm-book-download-links-link\" target=\"_blank\" target=\"_blank\" HREF=\"http://download.bookset.me/2017/7/%5B美%5D-雷·布拉德伯里-火星编年史-9787532775507.epub\">";
//        String reg =    "class=\"mbm-book-download-links-link\" target=\"_blank\" target=\"_blank\" HREF=\"http://download.bookset.me/(\\S*)\"";
//
//        List<String[]> bookUrlList = MatcherUtil.matchList(reg, url);
//
//        for(String[] ss:bookUrlList){
//            for(String s:ss){
//                System.out.println("value: "+ s);
//            }
//        }

        //获取所有书的类别信息, key为每个类别的名称
        Map<String, Map<String, String>> bookTypeMap = CrawlBookType.getBookType();


        for(String key : bookTypeMap.keySet()){
            String bookTypeFilePath = Constant.DOWNLOAD_DIR+key;
            Map<String, String> bookUrlMap = getBookUrl(bookTypeFilePath);

            crawlBookDownLoadUrl(bookUrlMap, key);
//            return;
        }

    }

    private static Map<String, String> getBookUrl(String bookTypeFilePath){
        String bookUrlFilePath = bookTypeFilePath+"\\"+"index.txt";

        String content = IOUtils.readToString(bookUrlFilePath);

        String[] contentArray = content.split("\r\n");

        Map<String, String> map = new HashMap<>();
        for(String s:contentArray){
            String key = s.split(", ")[0].split("：")[1];
            String url = s.split(", ")[1].split("：")[1];

            map.put(key, url);
        }

        return map;
    }

    private static void crawlBookDownLoadUrl(Map<String, String> bookUrlMap, String bookType){

        for(String key:bookUrlMap.keySet()){
            String url = bookUrlMap.get(key);

            while (true){
                try {
                    String downLoadUrl = getBookDownLoadUrl(url);
                    IOUtils.writeTxtFile(Constant.DOWNLOAD_DIR+bookType+"\\downloadUrl.txt", downLoadUrl);
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }

    }

    private static String getBookDownLoadUrl(String url) throws Exception {
        String bookDownLoadURL = HttpUtil.getHtml(url);

        String reg =    "class=\"mbm-book-download-links-link\" target=\"_blank\" target=\"_blank\" HREF=\"http://download.bookset.me/(\\S*)\"";
        List<String[]> bookUrlList = MatcherUtil.matchList(reg, bookDownLoadURL);

        if(bookUrlList==null || bookUrlList.size() == 0){
            reg =    "class=\"mbm-book-download-links-link\" target=\"_blank\" HREF=\"http://download.bookset.me/(\\S*)\"";
            bookUrlList = MatcherUtil.matchList(reg, bookDownLoadURL);
        }

        StringBuilder content = new StringBuilder();
        for(int i=0;i<bookUrlList.size();i++){
            String[] bookUrlArr=bookUrlList.get(i);
            String bookDownLoadUrl = bookUrlArr[0];

            String[] bookNameArr = bookDownLoadUrl.split("/");
            String[] bookNameArray = bookNameArr[bookNameArr.length-1].split("-");
            String bookName = "";

            try{
                bookName = bookNameArray[0]+bookNameArray[1];
            }catch (Exception ex){
                bookName = bookNameArr[bookNameArr.length-1];
                ex.printStackTrace();
            }

            content.append("书名：").append(URLDecoder.decode(bookName)).append(", 书URL：").append("http://download.bookset.me/").append(bookDownLoadUrl);
            if(i!=bookUrlList.size()-1){
                content.append("\r\n");
            }
        }

        return content.toString();
    }

}
