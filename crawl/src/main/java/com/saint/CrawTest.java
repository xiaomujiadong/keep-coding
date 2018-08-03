package com.saint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
/**
 * Created by wdcao on 2018/7/17.
 */
public class CrawTest {

    public static void main(String[] args) {

        String url_str = "https://bookset.me";
        URL url = null;
        try {
            url = new URL(url_str);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        String charset = "utf-8";
        int sec_cont = 1000;
        try {
            URLConnection url_con = url.openConnection();
            url_con.setDoOutput(true);
            url_con.setReadTimeout(10 * sec_cont);
            url_con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
            InputStream htm_in = url_con.getInputStream();

            String htm_str = InputStream2String(htm_in,charset);

            System.out.println(htm_str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String InputStream2String(InputStream in_st,String charset) throws IOException{
        BufferedReader buff = new BufferedReader(new InputStreamReader(in_st, charset));
        StringBuffer res = new StringBuffer();
        String line = "";
        while((line = buff.readLine()) != null){
            res.append(line);
        }
        return res.toString();
    }

    public void test(){
        
    }
}
