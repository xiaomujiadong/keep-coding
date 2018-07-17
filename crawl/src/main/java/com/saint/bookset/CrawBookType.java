package com.saint.bookset;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.saint.util.HttpUtil;
import com.saint.util.IOUtils;
import com.saint.util.MatcherUtil;

/**
 * 根据CrawIndex中的抓取出来的书类别信息进行再一次抓取
 *
 * 主要抓取每一本书的信息
 *
 * Created by wdcao on 2018/7/17.
 */
public class CrawBookType {

    public static void main(String[] args){

        Map<String, String> bookTypeMap = getBookType();

        for(String key : bookTypeMap.keySet()){
            String url = bookTypeMap.get(key);

            String fileContent = getBookHtml(url);

            try {
                IOUtils.writeTxtFile(Constant.DOWNLOAD_DIR+key+"\\index.txt", fileContent.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

    }

    private static Map<String, String> getBookType(){
        String downloadDir = Constant.DOWNLOAD_DIR+"index.txt";

        String content = IOUtils.readToString(downloadDir);

        String[] contentArray = content.split("\r\n");

        Map<String, String> map = new HashMap<>();
        for(String s:contentArray){
            String key = s.split(", ")[0].split("：")[1];
            String value = s.split(", ")[1].split("：")[1];
            map.put(key, value);
        }

        return map;
    }

    private static String getBookHtml(String url){
        try {
            String bookTypeIndexHtml = HttpUtil.getHtml(url);

            String reg = "<a href=\"https://bookset.me/(\\S*).html\" title=\"(\\S*)\">";
            List<String[]> bookUrlList = MatcherUtil.matchList(reg, bookTypeIndexHtml);

            StringBuilder content = new StringBuilder();
            for(String[] bookUrlArr:bookUrlList){
                String bookUrl = bookUrlArr[0];
                String bookName = bookUrlArr[1];

                content.append("书名：").append(bookName).append(", 书URL：").append("https://bookset.me/").append(bookUrl).append(".html").append("\r\n");
            }

            return content.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
