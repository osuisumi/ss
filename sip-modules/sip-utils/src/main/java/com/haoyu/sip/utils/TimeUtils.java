/**
 * 
 */
package com.haoyu.sip.utils;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.ocpsoft.prettytime.PrettyTime;

/**
 * @author lianghuahuang
 *
 */
public class TimeUtils {
	
	private String[] chineseDigit = {"一","二","三","四","五","六","七","八","九","十"};
	
	public static final String YEAR = "year";
	public static final String MONTH = "month";
	public static final String DATE = "date";
	public static final String HOUR = "hour";
	public static final String MINUTE = "minute";
	public static final String SECOND = "second";
	
	
	public static String prettyStartTime(Date startTime){
		if(startTime==null){
			return "";
		}
		Instant now = Instant.now();
		Duration duration = Duration.between(now, startTime.toInstant());
		return prettySeconds(duration.getSeconds());
	}
	
	public static String prettyEndTime(Date endTime){
		if(endTime==null){
			return "";
		}
		Instant now = Instant.now();
		Duration duration = Duration.between(now, endTime.toInstant());
		return prettySeconds(duration.getSeconds());
	}
	
	public static boolean hasBegun(Object... startTimes){
		for (Object startTime : startTimes) {
			if (startTime instanceof Date) {
				Date date = (Date) startTime;
				if(startTime != null){
					if (date.compareTo(new Date()) > 0) {
						return false;
					}
				}
			}
		}
		return true;
	}
	/*
	public static boolean hasBegun(Date startTime){
		if (startTime instanceof Date) {
			Date date = (Date) startTime;
			if(startTime != null){
				if (date.compareTo(new Date()) > 0) {
					return false;
				}
			}
		}
		return true;
	}
	*/
	public static boolean hasEnded(Object... endTimes){
		for (Object endTime : endTimes) {
			if (endTime instanceof Date) {
				Date date = (Date) endTime;
				if(endTime != null){
					if (date.compareTo(new Date()) < 0) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/*public static boolean hasEnded(Date endTime){
			if (endTime instanceof Date) {
				Date date = (Date) endTime;
				if(endTime != null){
					if (date.compareTo(new Date()) < 0) {
						return true;
					}
				}
			}
		return false;
	}
	*/

	/**
	 * 显示秒值为**年**月**天 **时**分**秒 如1年2个月3天 10小时
	 *
	 * @return
	 */
	public static final String prettySeconds(long totalSeconds) {
		StringBuilder s = new StringBuilder();
		long second = totalSeconds % 60;
		if (totalSeconds > 0) {
			if (totalSeconds < 24 * 3600) {
				s.append("秒");
				s.append(StringUtils.reverse(String.valueOf(second)));
			}
		}

		totalSeconds = totalSeconds / 60;
		long minute = totalSeconds % 60;
		if (totalSeconds > 0) {
			s.append("分");
			s.append(StringUtils.reverse(String.valueOf(minute)));
		}

		totalSeconds = totalSeconds / 60;
		long hour = totalSeconds % 24;
		if (totalSeconds > 0) {
			s.append(StringUtils.reverse("小时"));
			s.append(StringUtils.reverse(String.valueOf(hour)));
		}

		totalSeconds = totalSeconds / 24;
		long day = totalSeconds % 31;
		if (totalSeconds > 0) {
			s.append("天");
			s.append(StringUtils.reverse(String.valueOf(day)));
		}

		totalSeconds = totalSeconds / 31;
		long month = totalSeconds % 12;
		if (totalSeconds > 0) {
			s.append("月");
			s.append(StringUtils.reverse(String.valueOf(month)));
		}

		totalSeconds = totalSeconds / 12;
		long year = totalSeconds;
		if (totalSeconds > 0) {
			s.append("年");
			s.append(StringUtils.reverse(String.valueOf(year)));
		}
		return s.reverse().toString();
	}

	/**
	 * 美化时间 如显示为 1小时前 2分钟前
	 *
	 * @return
	 */
	public static final String prettyTime(Date date) {
		PrettyTime p = new PrettyTime(Locale.SIMPLIFIED_CHINESE);
		return p.format(date);

	}

	public static final String prettyTime(Date date, String localeName) {
		Locale locale = Locale.getDefault();
		if (localeName != null && localeName.equals("en")) {
			locale = Locale.ENGLISH;
		} else if (localeName != null && localeName.equals("zh_CN")) {
			locale = Locale.SIMPLIFIED_CHINESE;
		}
		PrettyTime p = new PrettyTime(locale);
		return p.format(date);
	}

	public static final String prettyTime(long millisecond) {
		PrettyTime p = new PrettyTime(Locale.SIMPLIFIED_CHINESE);
		return p.format(new Date(millisecond));
	}
	
	public static String formatDate(long timeMillis,String pattern){
		if(StringUtils.isEmpty(pattern)){
			pattern = "yyyy-MM-dd";
		}
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(timeMillis);
		SimpleDateFormat dateformat =new SimpleDateFormat(pattern);  
		return dateformat.format(c.getTime());
	}
	
	public static String formatDate(long timeMillis,String pattern,String localeName){
		if(StringUtils.isEmpty(pattern)){
			pattern = "yyyy-MM-dd";
		}
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(timeMillis);
		SimpleDateFormat dateformat =new SimpleDateFormat(pattern);  
		return dateformat.format(c.getTime());
	}
	
	public static String formatDate(Date date,String pattern){
		if(StringUtils.isEmpty(pattern)){
			pattern = "yyyy-MM-dd";
		}
		SimpleDateFormat dateformat =new SimpleDateFormat(pattern);  
		return dateformat.format(date);
	}

	/**
	 * 获取本周第一天的日期
	 * @return
	 */
	public static Date getWeekBeginDate() {
		int mondayPlus;
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
		if (dayOfWeek == 1) {
			mondayPlus = 0;
		} else {
			mondayPlus = 1 - dayOfWeek;
		}
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus); 
		currentDate.set(Calendar.HOUR_OF_DAY, 0);  
		currentDate.set(Calendar.MINUTE, 0);  
		currentDate.set(Calendar.SECOND, 0);  
		currentDate.set(Calendar.MILLISECOND, 0);  
        return currentDate.getTime();  
	}
	
	/**
	 * 获取本月第一天的日期
	 * @return
	 */
	public static Date getMonthBeginDate() {
		int mondayPlus;
		Calendar cd = Calendar.getInstance();
		int dayOfMonth = cd.get(Calendar.DAY_OF_MONTH);
		if (dayOfMonth == 1) {
			mondayPlus = 0;
		} else {
			mondayPlus = 1 - dayOfMonth;
		}
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		currentDate.set(Calendar.HOUR_OF_DAY, 0);  
		currentDate.set(Calendar.MINUTE, 0);  
		currentDate.set(Calendar.SECOND, 0);  
		currentDate.set(Calendar.MILLISECOND, 0);  
        return currentDate.getTime();  
	}
	
	/**
	 * 获取传入的时间参数的年,月,日,时,分,秒
	 */
	public static int getFieldValue(Object date, String field){
		if (date instanceof Date) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime((Date)date);
			if (YEAR.equals(field)) {
				return calendar.get(Calendar.YEAR);
			}else if (MONTH.equals(field)) {
				return calendar.get(Calendar.MONTH) + 1;
			}else if (DATE.equals(field)) {
				return calendar.get(Calendar.DATE);
			}else if (HOUR.equals(field)) {
				return calendar.get(Calendar.HOUR_OF_DAY);
			}else if (MINUTE.equals(field)) {
				return calendar.get(Calendar.MINUTE);
			}else if (SECOND.equals(field)) {
				return calendar.get(Calendar.SECOND);
			}
		}
		return 0;
	}
	
	
	//获取月份的第一天
	public static Date getFirstDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
		return cal.getTime();
	}
	
	//获取月份最后一天
	public static Date getLastDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
		return cal.getTime();
	}
	
	//年份第一天
	public static Date getFirstDayOfYear(int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year) ;
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
		return cal.getTime();
	}
	
	//年份最后一天
	public static Date getLastDayOfYear(int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year) ;
		cal.set(Calendar.MONTH, cal.getActualMaximum(Calendar.MONTH));
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
		return cal.getTime();
	}
	
	public static Date calendarToDate(Calendar calendar){
		return calendar.getTime();
	}
	
	//获取两个日期之间的天数
	public static int getBetweenDays(Date d1, Date d2) {
		if (d1 == null || d2 == null) {
			return -1;
		}
		int betweenDays;
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		// 保证第二个时间一定大于第一个时间
		if (c1.after(c2)) {
			c2.setTime(d1);
			c1.setTime(d2);
		}
		int betweenYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
		betweenDays = c2.get(Calendar.DAY_OF_YEAR) - c1.get(Calendar.DAY_OF_YEAR);
		for (int i = 0; i < betweenYears; i++) {
			c1.set(Calendar.YEAR, (c1.get(Calendar.YEAR) + 1));
			betweenDays += c1.getMaximum(Calendar.DAY_OF_YEAR);
		}
		return betweenDays;
	}
	
	public static int getBetweenDaysFromNow(Date d1){
		return getBetweenDays(new Date(), d1);
	}
	
}
