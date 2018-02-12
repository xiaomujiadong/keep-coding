package alitest;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 计算交易类工具
 *
 * 大概思路，先将传入的大量date通过排序变成一个有序集合，之后通过二分查找。在排序后的数组里面找出最接近的值
 *
 */
public class TradeDateUtil {

    //计算属于交易日的时间
    private static final long START_AM = 9 * 3600 * 1000 + 1800 * 1000;
    //计算属于交易日的时间
    private static final long END_PM = 15 * 3600 * 1000;

    //符合日期的长度必须是8
    private static final int STR_TRADE_DAT_LENGTH = 8;

    //用来连接年月日
    private static final String LINK_STR = "-";

    //用来截取年的开始索引
    private static final int START_INDEX = 0;
    //用来截取年的开始索引
    private static final int YEAR_INDEX = 4;
    //用来截取月的开始索引
    private static final int MONTH_INDEX= 6;
    //用来截取日的开始索引
    private static final int DAY_INDEX = 8;

    //用来存放交易的缓存
    private static List<TradeTime> tradeDateList = null;

    public static void main(String[] args){
        List<String> tradyDayList = new ArrayList<String>();
        tradyDayList.add("20180102");
        tradyDayList.add("20180103");
        tradyDayList.add("20180104");
        tradyDayList.add("20180105");
        tradyDayList.add("20180108");
        tradyDayList.add("20180109");

        init(tradyDayList);

        int offsetDays = -1;
        String targetTime = "2018-01-05 01:00:10";
        Date date  = new Date(Timestamp.valueOf(targetTime).getTime());

        System.out.println(targetTime+" 的 T "+(offsetDays>=0?("+"+offsetDays):offsetDays)+" 交易日： " + getTradeDay(date, offsetDays));
    }

    //: TODO 可自行定义需要的变量
    /**
     * 工具初始化，初始化的目的是让工具具备更加合适各的数据结构，方便计算提高效率
     * @param tradeDayList 包含一年内所有的交易日起，格式如：20160701 20160704 20160705，非交易日20160702 20160703不在其中.
     */
    public static void init (List<String> tradeDayList){
        if(tradeDayList == null)
            return;

        synchronized(TradeDateUtil.class) {
            StringBuilder dateBuilder = new StringBuilder();
            List<TradeTime> realDateList = new ArrayList<TradeTime>();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            for(int i=0; i<tradeDayList.size(); i++){
                String tradeDay = tradeDayList.get(i);
                if(tradeDay.length() != STR_TRADE_DAT_LENGTH ){
                    System.out.println("输入的交易日期不合法，输入的值为： "+tradeDay+", 正确的形式为： 20160701 ");
                    continue;
                }
                try{
                    dateBuilder.append(tradeDay.substring(START_INDEX, YEAR_INDEX)).append(LINK_STR);
                    dateBuilder.append(tradeDay.substring(YEAR_INDEX, MONTH_INDEX)).append(LINK_STR);
                    dateBuilder.append(tradeDay.substring(MONTH_INDEX, DAY_INDEX));

                    Date date = dateFormat.parse(dateBuilder.toString());
                    TradeTime tradeTime = new TradeTime(date.getTime(), tradeDay);
                    realDateList.add(tradeTime);
                    dateBuilder.delete(0, dateBuilder.toString().length());
                }catch (Exception ex){
                    System.out.println("异常， 输入的交易日期不合法，输入的值为： "+tradeDay+", 正确的形式为： 20160701 ");
                }
            }
            if(realDateList != null){
                TradeTime tempDate = null;
                for(int i=0; i<realDateList.size(); i++){
                    for (int j = 0; j < i; ++j){
                        if (realDateList.get(j + 1).getTime() < realDateList.get(j).getTime()){
                            tempDate = realDateList.get(j);
                            realDateList.add(j, realDateList.get(j+1));
                            realDateList.add(j+1, tempDate);
                        }
                    }
                }
                tradeDateList = realDateList;
            }
        }
    }

    /**
     *
     * 给定任意时间，返回给定时间的T+n交易日
     *
     * @param time 给定要计算的时间。
     * @param offsetDays 交易日偏移量，offsetDays可以为负数，表示T-n的计算。
     */
    public static String getTradeDay(Date time, int offsetDays){
        int lo=0;
        int hi=tradeDateList.size()-1;
        int mid = (lo+hi)/2;
        long longTime = time.getTime();

        //二分法，进行查找对应的时间
        while(lo<=hi){
            mid=(lo+hi)/2;
            if ((mid+1) >= tradeDateList.size()) {
                break;
            }
            if(tradeDateList.get(mid+1).getTime() == longTime){
                mid = mid +1;
                break;
            }else if(tradeDateList.get(mid).getTime() <= longTime && (mid+1)<=tradeDateList.size()-1 && longTime <tradeDateList.get(mid + 1 ).getTime() ){
                break;
            }else if(tradeDateList.get(mid).getTime() < longTime && longTime > tradeDateList.get(mid + 1 ).getTime()){
                lo=mid+1;
            }else{
                hi=mid-1;
            }
        }
        TradeTime tradeTime = tradeDateList.get(mid);

        //表示最近交易日不是当天
        if(tradeTime.getEndPM() < time.getTime()){
            mid = mid + 1;
        }

        mid = mid + offsetDays;

        if(0<= mid && mid < tradeDateList.size()){
            return tradeDateList.get(mid).getDate();
        }else{
            return "无合适的交易日";
        }

    }

    //用来存放时间，用来排序和查找的时候，long形的时间便于比较
    static class TradeTime{
        private Long time;
        private Long startAM;
        private Long endPM;
        private String date;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public long getTime() {
            return time;
        }

        public void setTime(Long time) {
            this.time = time;
        }

        public TradeTime(long dateTime, String date) {
            this.time = dateTime;
            this.startAM = dateTime + START_AM;
            this.endPM = dateTime + END_PM;
            this.date = date;
        }

        public Long getStartAM() {
            return startAM;
        }

        public void setStartAM(Long startAM) {
            this.startAM = startAM;
        }

        public Long getEndPM() {
            return endPM;
        }

        public void setEndPM(Long endPM) {
            this.endPM = endPM;
        }
    }
}
