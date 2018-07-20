package com.saint.util;

import com.saint.bookset.Constant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * Created by wdcao on 2018/7/17.
 */
public class HttpUtil {

    private static Stack<String> ipStack = new Stack<>();

    private static Map<String, Integer> map = new HashMap<>();

    static {
//        ipStack.push("61.136.163.245");
//        ipStack.push("54.222.177.145");
//        ipStack.push("117.127.0.195");
//        ipStack.push("118.24.157.22");

//        ipStack.push("202.121.178.244");

        ipStack.push("58.220.95.107");

        map.put("61.136.163.245", 8103);
        map.put("54.222.177.145", 3128);
        map.put("117.127.0.195", 8080);
        map.put("202.121.178.244", 8080);
        map.put("118.24.157.22", 3128);

        map.put("58.220.95.107", 8080);
    }

    public static void main(String[] args){
        String url = "https://bookset.me";

        long start = System.currentTimeMillis();

        try {
            String html = getHtmpByProxyIp(url);
            System.out.println("content: "+html);
        } catch (Exception e) {
            e.printStackTrace();
        }

        long totalTime = System.currentTimeMillis()-start;
        System.out.println("totalTime: "+totalTime);
    }

    public static String getHtmpByProxyIp(String urlStr)throws Exception{
        String host = ipStack.pop();
        Integer port = map.get(host);

        SocketAddress address = new InetSocketAddress(host, port) ;
        Proxy proxy = new Proxy(Proxy.Type.HTTP, address);

        URL url = new URL(urlStr);

        String charset = "utf-8";
        int sec_cont = 1000;

        URLConnection urlConnection = url.openConnection(proxy);
        urlConnection.setDoOutput(true);
        urlConnection.setReadTimeout(10 * sec_cont);
        urlConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
        InputStream inputStream = urlConnection.getInputStream();

        String htmlContent = inputStream2String(inputStream,charset);

        return htmlContent;
    }


    //根据URL地址获取网页内容
    public static String getHtml(String urlStr)throws Exception{
        URL url = new URL(urlStr);

        String charset = "utf-8";
        int sec_cont = 1000;

        URLConnection urlConnection = url.openConnection();
        urlConnection.setDoOutput(true);
        urlConnection.setReadTimeout(10 * sec_cont);
        urlConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
        InputStream inputStream = urlConnection.getInputStream();

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
