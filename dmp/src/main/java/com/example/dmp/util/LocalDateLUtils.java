package com.example.dmp.util;

import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName:    LocalDateLUtils
 * @Description:  TODO
 * @Author:       王志彪(Will Wang)
 * @Date:         2019/11/13 11:37
 * @Version:      V1.0
 * @Since:        V1.0
 */
@Slf4j
public class LocalDateLUtils {

    /**
     * 定义时间格式常量(页面格式)
     **/
    public static final String yyyy_MM                = "yyyy-MM";
    public static final String yyyy_MM_dd             = "yyyy-MM-dd";
    public static final String yyyy_MM_dd_HH_mm       = "yyyy-MM-dd HH:mm";
    public static final String yyyy_MM_dd_HH_mm_ss    = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyy_MM_dd_HH_mm_ss_SS = "yyyy-MM-dd HH:mm:ss.SS";
    public static final String HH_mm_ss = "HH:mm:ss";
    public static final String HH_mm = "HH:mm";


    /**
     * 定义时间格式常量(非格式)
     **/
    public static final String yyyyMMdd               = "yyyyMMdd";
    public static final String yyyyMMddHHmmss         = "yyyyMMddHHmmss";
    public static final String yyyyMMddHHmmssSS      = "yyyyMMddHHmmssSS";
    public static final String MM_dd                ="MM-dd";
    public static final String mmss                ="mmss";

    /**
     * @return java.lang.Integer
     * @Author renqy
     * @Description 根据生日计算年龄
     * @Date 2019/3/14 11:42
     * @Param [birthday]
     **/
    public static Integer getAgeByBirthday(LocalDate birthday) {
        LocalDate now = LocalDate.now();
        if (now.isBefore(birthday)) {
            return 0;
        }
        int nowYear = now.getYear();
        int nowMonth = now.getMonthValue();
        int nowDay = now.getDayOfMonth();
        int birthYear = birthday.getYear();
        int birthMonth = birthday.getMonthValue();
        int birthDay = birthday.getDayOfMonth();
        int age = nowYear - birthYear;
        if (nowMonth == birthMonth) {
            if (nowDay < birthDay) {
                age--;
            }
        } else if (nowMonth < birthMonth) {
            age--;
        }
        return age;

    }

    /**
     * @Description: 计算两个时间相差的天数
     * @author zhangwei04
     * @param: [startTime, endTime]
     * @return: int
     * 2019/3/14
     * @since V1.0
     */
    public static int getDays(LocalDateTime startTime, LocalDateTime endTime) {
        Long days = endTime.toLocalDate().toEpochDay() - startTime.toLocalDate().toEpochDay();
        return days.intValue();
    }

    /**
     * @description 将日期转成指定的格式
     * @author zhangwei04
     * @date 2019/3/22 18:50
     * @param dateTime
     * @return java.lang.String
    */
    public static String dateToString(LocalDateTime dateTime,String pattern){
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        try {
            return df.format(dateTime);
        }catch (Exception e){
            return "";
        }
    }
    /**
     * @description 将字符串转成指定格式 精确到日月年
     * @author zhangqi01
     * @date 2019/4/26 11:32
     * @param
     * @return
     */
    public static LocalDate stringToDate(String day,String pattern){
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(day,df);
    }

    /**
     * @description 时间戳转日期
     * @author houkj
     * @date 2019/5/17 16:53
     * @param timestamp
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime getDateTimeOfTimestamp(Integer timestamp) {
        if(null == timestamp){
            return null;
        }
        Instant instant = Instant.ofEpochSecond(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /** 
     * @description 默认格式化
     * @author yangzhi
     * @date 2019/3/27 10:42
     * @param dateTime
     * @return java.lang.String
     */
    public static String dateToString(LocalDateTime dateTime){
        return dateToString(dateTime,yyyy_MM_dd_HH_mm_ss);
    }

    /** 
     * @description 获取当天剩余的毫秒值
     * @author yangzhi
     * @date 2019/7/10 11:49
     * @param 
     * @return long
     */
    public static long getTodayTimeout() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tomorrow = LocalDateTime.of(now.plusDays(1).toLocalDate(),LocalTime.of(0,0));
        //当天剩余的毫秒值
        return tomorrow.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() - now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * @description 将String类型（2019-07-09 12:00:00/2019-07-09）的时间转成localDate格式 的时间
     * @author zhangwei04
     * @date 2019/7/22 9:39
     * @param date
     * @return java.time.LocalDate
    */
    public static LocalDate stringToLocalDate(String date){
        LocalDate localDate = null;
        try {
            if(EmptyUtils.isNotEmpty(date)) {
                if (date.contains(" ")) {
                    int a = date.lastIndexOf(" ");
                    String date1 = date.substring(0, a);
                    localDate = LocalDate.parse(date1);
                } else {
                    localDate = LocalDate.parse(date);
                }
            }
            return localDate;
        } catch (Exception e) {
            log.info("stringToLocalDate时间格式转换错误！");
        }
        return null;
    }

    /**
     * @param date
     * @return java.time.LocalDate
     * @description 将String类型（2019-07-09 12:00:00/2019-07-09）的时间转成localDatetTme格式 的时间
     * @author zhangwei04
     * @date 2019/7/22 9:39
     */
    public static LocalDateTime stringToLocaldateTime(String date) {
        LocalDateTime localDateTime = null;
        try {
            if(EmptyUtils.isNotEmpty(date)) {
                if (date.contains(" ")) {
                    DateTimeFormatter df = DateTimeFormatter.ofPattern(LocalDateLUtils.yyyy_MM_dd_HH_mm_ss.trim());
                    localDateTime = LocalDateTime.parse(date, df);
                } else {
                    LocalDate localDate = LocalDate.parse(date);
                    localDateTime = LocalDateTime.of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth(), 00, 00, 00);
                }
            }
            return localDateTime;
        } catch (Exception e) {
           log.info("stringToLocalDateTime时间格式转换错误！");
        }
        return null;
    }

    /**
     * @description  时间错毫秒数转LocalDateTime
     * @author raogang
     * @date 2019-8-13 10:32
     * @param
     * @return
    */
    public static LocalDateTime timestampChangeToLocalDateTime(Long timestamp) {
        if(null == timestamp){
            return null;
        }
        LocalDateTime localDateTime = Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
        return localDateTime;
    }

}
