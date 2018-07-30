package com.saint.bookset;

import com.saint.util.ZipUtils;

import java.io.File;
import java.util.Map;

/**
 * Created by wdcao on 2018/7/24.
 */
public class ZipBookSet {

    public static void main(String[] args){
        String srcFileUrl = "E:\\开发工具和资料压缩包\\bookset\\";
        Map<String, Map<String, String>> bookTypeMap = CrawlBookType.getBookType();

        try{

            for(String key:bookTypeMap.keySet()){
                String srcFile = srcFileUrl + key;
                String zipFileName = "E:\\开发工具和资料压缩包\\压缩\\"+key+".zip";

                File file = new File(zipFileName);
                if(file.exists()){
                    System.out.println("文件已经存在： "+zipFileName+", srcFile: "+srcFile);
                    continue;
                }

                System.out.println("-----文件不存在： "+zipFileName+", srcFile: "+srcFile);

//                System.out.println("srcFile: "+srcFile+", zipFileName: "+zipFileName);
                ZipUtils.zip(zipFileName, srcFile);
            }

//            String zipFileName = "E:\\开发工具和资料压缩包\\传记.zip";
//            String srcFileName = "E:\\开发工具和资料压缩包\\bookset\\传记";
//
//            ZipUtils.zip(zipFileName, srcFileName);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
