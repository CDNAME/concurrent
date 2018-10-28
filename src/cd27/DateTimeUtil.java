package cd27;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ChenDeng
 * 2018-10-06 18:15
 */
public final class DateTimeUtil {
    private DateTimeUtil() {
        throw new AssertionError();
    }
    
    //获取时分秒
    public static void getNowTimeDetail() {
        Calendar cal = Calendar.getInstance();
        System.out.println(cal.get(Calendar.YEAR));
        System.out.println(cal.get(Calendar.MONTH) + 1);        //0-11
        System.out.println(cal.get(Calendar.DATE));
        System.out.println(cal.get(Calendar.HOUR_OF_DAY));
        System.out.println(cal.get(Calendar.MINUTE));
        System.out.println(cal.get(Calendar.SECOND));
        
        //Java 8
        LocalDateTime dt = LocalDateTime.now();
        System.out.println(dt.getYear());
        System.out.println(dt.getMonthValue());
        System.out.println(dt.getDayOfMonth());
        System.out.println(dt.getHour());
        System.out.println(dt.getMinute());
        System.out.println(dt.getSecond());
    }
    
    //获取从1970年1月1日0时0分0秒到现在的毫秒数
    public static void getTimeMillis() {
        System.out.println(Calendar.getInstance().getTimeInMillis());

        System.out.println(System.currentTimeMillis());
        
        //Java 8
        System.out.println(Clock.systemDefaultZone().millis());
    }
    
    //获取月的最后一天
    public static void getLastDay() {
        Calendar time = Calendar.getInstance();
        System.out.println(time.getActualMaximum(Calendar.DAY_OF_MONTH));
        LocalDate dt = LocalDate.now();
        LocalDate firstDay = dt.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDay = dt.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("本月的第一天是" + firstDay + "; 最后一天是" + lastDay);
        
        //去指定月份的第一个周一是几号
        LocalDate firstMonday = LocalDate.parse("2018-10-01").with(
                TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        System.out.println(firstMonday);
    }
    
    //格式化日期
    public static void formatDate() {
        SimpleDateFormat oldFormatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date1 = new Date();
        System.out.println(oldFormatter.format(date1));
        
        //Java 8
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date2 = LocalDate.now();
        System.out.println(date2.format(newFormatter));
    }
    
    //获取昨天的当前时刻
    public static void yesterdayCurrent() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        System.out.println(cal.getTime());
        
        //Java 8
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime yesterday = today.minusDays(1);
        System.out.println(yesterday);
        
    }
}
