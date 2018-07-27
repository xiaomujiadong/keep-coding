package com.saint.bookset;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
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
public class CrawlBookType {

    public static void main(String[] args){

        Map<String, Map<String, String>> bookTypeMap = getBookType();

        for(String key : bookTypeMap.keySet()){
            String fileName = Constant.DOWNLOAD_DIR+key+"\\index.txt";
            File file = new File(fileName);
            if (file.exists()) {
                System.out.println(new Timestamp(System.currentTimeMillis()).toString()+" 文件已经存在： "+fileName);
                continue;
            }
            Map<String, String> innerMap = bookTypeMap.get(key);
            //每个类别的URL地址
            String url = innerMap.get("url");
            //每个类别的书有的总数
            int bookCount = Integer.valueOf(innerMap.get("bookCount"));

            int page = 0;
            int pageSize = 20;

            while(bookCount>0){
                ++page;
                bookCount = bookCount - pageSize;
            }

            //分页查询每个类别下的书
            for(int i=1; i<=page; i++){
                String tempUrl = url+"/page/"+i;

                String fileContent = null;
                try {
                    fileContent = getBookHtml(tempUrl);
                } catch (Exception e) {
                    e.printStackTrace();
                    --i;
                    continue;
                }

                try {

                    IOUtils.writeTxtFile(fileName, fileContent.toString());
                } catch (IOException e) {
                    --i;
                    e.printStackTrace();
                    continue;
                }
                try {
                    Thread.sleep(1 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static Map<String, Map<String, String>> getBookType(){
        String downloadDir = Constant.DOWNLOAD_DIR+"index.txt";

        String content = IOUtils.readToString(downloadDir);

        String[] contentArray = content.split("\r\n");

        Map<String, Map<String, String>> map = new HashMap<>();
        for(String s:contentArray){
            Map<String, String> innerMap = new HashMap<>();
            String key = s.split(", ")[0].split("：")[1];
            String url = s.split(", ")[1].split("：")[1];
            String bookCount = s.split(", ")[2].split(" ")[1];

            innerMap.put("url", url);
            innerMap.put("bookCount", bookCount);

            map.put(key, innerMap);
        }

        return map;
    }

    private static String getBookHtml(String url) throws Exception {
        String bookTypeIndexHtml = HttpUtil.getHtml(url);

        String reg = "<a href=\"https://bookset.me/(\\S*).html\" alt=\"(\\S*)\"";
        List<String[]> bookUrlList = MatcherUtil.matchList(reg, bookTypeIndexHtml);

        StringBuilder content = new StringBuilder();
        for(int i=0;i<bookUrlList.size();i++){
            String[] bookUrlArr=bookUrlList.get(i);
            String bookUrl = bookUrlArr[0];
            String bookName = bookUrlArr[1];

            content.append("书名：").append(bookName).append(", 书URL：").append("https://bookset.me/").append(bookUrl).append(".html");
            if(i!=bookUrlList.size()-1){
                content.append("\r\n");
            }
        }

        return content.toString();
    }
}
