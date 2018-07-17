package com.saint.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wdcao on 2018/07/17.
 */
public class MatcherUtil {

    /**
     * 匹配字符串，返回匹配的结果list
     *
     * @param origin
     * @param reg
     * @return
     */
    public static List<List<String>> matcher(String origin, String reg) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(origin);
        List<List<String>> lists = new LinkedList<>();
        int i = 0;
        while (matcher.find()) {
            LinkedList<String> linkedList = new LinkedList<>();
            for (int j = 0; j < matcher.groupCount(); j++) {
                linkedList.add(matcher.group(j + 1));
            }
            lists.add(linkedList);
        }
        return lists;
    }

    public static List<String[]> matchList(String preg, String content) {
        Pattern p = Pattern.compile(preg);
        return matchList(p, content);
    }

    public static List<String[]> matchList(Pattern pattern, String content) {
        Matcher m = pattern.matcher(content);
        ArrayList list = new ArrayList();

        while (m.find()) {
            if (m.groupCount() > 0) {
                String[] s = new String[m.groupCount()];
                for (int i = 0; i < m.groupCount(); i++) {
                    s[i] = m.group(i + 1);
                }
                list.add(s);
            }
        }
        return list;
    }

    public static List<String> matchSingleList(String preg, String content) {
        Pattern p = Pattern.compile(preg);
        return matchSingleList(p, content);
    }

    public static List<String> matchSingleList(Pattern pattern, String content) {
        Matcher m = pattern.matcher(content);
        ArrayList list = new ArrayList();
        while (m.find()) {
            String s = m.group(1);
            list.add(s);
        }
        return list;
    }

    public static String matchString(String preg, String content) {
        Pattern p = Pattern.compile(preg);
        return matchString(p, content);
    }

    public static String matchString(Pattern pattern, String content) {
        Matcher m = pattern.matcher(content);
        if (m.find()) {
            return m.groupCount() > 0 ? m.group(1) : null;
        } else {
            return null;
        }
    }

    public static List<String> matchStub(String preg, String content) {
        Pattern p = Pattern.compile(preg);
        return matchStub(p, content);
    }

    public static List<String> matchStub(Pattern pattern, String content) {
        Matcher m = pattern.matcher(content);
        List<String> list = new ArrayList();
        if (m.find()) {
            if (m.groupCount() > 0) {
                for (int i = 0; i < m.groupCount(); ++i) {
                    list.add(m.group(i + 1));
                }
            }
        }
        return list;
    }

    public static boolean isMatch(String preg, String content) {
        Pattern p = Pattern.compile(preg);
        return isMatch(p, content);
    }

    public static boolean isMatch(Pattern pattern, String content) {
        try {
            Matcher matcher = pattern.matcher(content);
            return matcher.find();
        } catch (Exception ex) {
            return false;
        }
    }
}
