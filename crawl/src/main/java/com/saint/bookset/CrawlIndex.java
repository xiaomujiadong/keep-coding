package com.saint.bookset;

import com.saint.util.HttpUtil;
import com.saint.util.IOUtils;
import com.saint.util.MatcherUtil;

import java.io.File;

import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**主要把bookset的首页中各个分页记录下来了
 *
 * index.txt中的数据内容为： 书类别：哲学, 类别URL：https://bookset.me/tag/%E5%93%B2%E5%AD%A6, 共有 93 本书
 *
 * Created by wdcao on 2018/7/17.
 */
public class CrawlIndex {

    public static void main(String[] args) {
        try {
            String htmlContent = HttpUtil.getHtml(Constant.TAG_URL);

            //匹配出书的类别信息
//            String pattern = "a title=\"(\\d+)个话题\" target=\"_blank\" href=\"https://bookset.me/tag/(\\S*)\"";
//            String pattern = "a href='https://bookset.me/tag/(\\S*)'";

            String pattern = "<a href='https://bookset.me/tag/(\\S*)' title='(\\S*) Tag' class='(\\S*)' style='color:#666'>(\\S*) \\((\\d+)\\)</a>";

            List<String[]> list = MatcherUtil.matchList(pattern, htmlContent);

            Map<String, String> map = new HashMap<>();
            StringBuilder content = new StringBuilder();
            for(String[] strA:list){
                String value = "书类别："+URLDecoder.decode(strA[1])+", 类别URL：https://bookset.me/tag/"+strA[1]+", 共有 "+strA[4]+" 本书\r\n";
                    map.put(URLDecoder.decode(strA[1]), value);

                String bookType = URLDecoder.decode(strA[1]);
                if(('a'<=bookType.charAt(0) && bookType.charAt(0) <= 'z') || ('A'<=bookType.charAt(0) && bookType.charAt(0) <= 'Z')){
                    continue;
                }

                String bookTypeDir = Constant.DOWNLOAD_DIR + bookType;
                String ts = map.get(bookType);
                content.append(ts);
                File file = new File(bookTypeDir);
                if(file.exists()){
                    System.out.println(new Timestamp(System.currentTimeMillis())+" 目录已经存在： "+bookTypeDir);
                    continue;
                }
                file.mkdir();

                System.out.println("value: "+URLDecoder.decode(ts));
            }
            IOUtils.writeTxtFile(Constant.DOWNLOAD_DIR+"index.txt", content.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
