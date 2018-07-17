package com.saint.util;

import com.saint.bookset.Constant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by wdcao on 2018/7/17.
 */
public class HttpUtil {

    //根据URL地址获取网页内容
    public static String getHtml(String urlStr)throws Exception{
        URL url = new URL(urlStr);

        String charset = "utf-8";
        int sec_cont = 1000;

        URLConnection url_con = url.openConnection();
        url_con.setDoOutput(true);
        url_con.setReadTimeout(10 * sec_cont);
        url_con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
        InputStream inputStream = url_con.getInputStream();

        String htmlContent = inputStream2String(inputStream,charset);

        return htmlContent;
    }

    public static String inputStream2String(InputStream in_st,String charset) throws IOException {
        BufferedReader buff = new BufferedReader(new InputStreamReader(in_st, charset));
        StringBuffer res = new StringBuffer();
        String line = "";
        while((line = buff.readLine()) != null){
            res.append(line);
        }
        return res.toString();
    }
}
