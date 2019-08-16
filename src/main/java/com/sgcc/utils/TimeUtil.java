package com.sgcc.utils;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class TimeUtil {
	
	private static final Logger log = LoggerFactory.getLogger(TimeUtil.class);
	
	public static final String DATE_TIME_17 = "yyyyMMddHHmmssSSS";
	public static final String DATE_TIME_14 = "yyyyMMddHHmmss";
	public static final String DATE_TIME_12_ = "yyMMddHHmmss";
	public static final String DATE_TIME_12 = "yyyyMMddHHmm";
	public static final String DATE_8 = "yyyyMMdd";
	public static final String FORMAT_DATE_10 = "yyyy-MM-dd";
	public static final String FORMAT_DATE_TIME_19 = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_DATE_TIME_16 = "yyyy.MM.dd HH:mm";
	public static final String FORMAT_DATE_TIME1_16 = "yyyy-MM-dd HH:mm";
	public static final String FORMAT_TIME_9 = "HHmmssSSS";
	public static final String FORMAT_TIME_6 = "HH:mm:ss";
	public static final String FORMAT_TIME_14_CHINESE = "yyyy年MM月dd日 HH:mm:ss";
	public static final String FORMAT_TIME_8_CHINESE = "yyyy年MM月dd日";
	
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	static {
		dateFormat.setLenient(false);//这个的功能是不把2014-13-3 转换为2015-1-3
	}
	private static int[] DAY_COUNTS_OF_MONTH = new int[] { 31, 59, 90, 120, 151,
			181, 212, 243, 273, 304, 334, 365 };

	
	public static String getFormatedCurrentDate12_() {
		SimpleDateFormat dateSDF = new SimpleDateFormat(DATE_TIME_12_);
		return dateSDF.format(new java.util.Date());
	}
	public static String getFormatedCurrentDate() {
		SimpleDateFormat dateSDF = new SimpleDateFormat(DATE_8);
		return dateSDF.format(new java.util.Date());
	}

	public static String getFormatedCurrentDateOfChiness() {
		SimpleDateFormat dateSDF = new SimpleDateFormat("yyyy年MM月dd日");

		return dateSDF.format(new java.util.Date());
	}

	/**
	 * date 格式yyyyMMdd字符串 转换为yyyy年MM月dd日
	 * 
	 * @param date
	 * @return
	 */
	public static String getFormatedCurrentDateOfChiness(String dateStr) {
		SimpleDateFormat dateSDF8 = new SimpleDateFormat(DATE_8);
		SimpleDateFormat dateSDF = new SimpleDateFormat("yyyy年MM月dd日");
		try {
			Date d = dateSDF8.parse(dateStr);
			return dateSDF.format(d);
		} catch (ParseException e) {
			throw new RuntimeException("日期格式不正确",e);
		}
	}

	public static String getFormatedDate(long time) {
		SimpleDateFormat dateSDF = new SimpleDateFormat(DATE_8);
		return dateSDF.format(new java.util.Date(time));
	}

	public static String getFormatedDate6(String date) {
		String rtDate = date.replaceAll("-", "");
		rtDate = rtDate.substring(2, 8);
		return rtDate;
	}

	public static String getFormatedDate(java.util.Date date) {
		SimpleDateFormat dateSDF = new SimpleDateFormat(DATE_8);
		return dateSDF.format(date);
	}

	public static String getFormatedDate(int year, int month, int date) {
		return Integer.toString(year)
				+ (month >= 10 ? Integer.toString(month) : "0" + month)
				+ (date >= 10 ? Integer.toString(date) : "0" + date);
	}

	public static String getFormatedMonth(int year, int month) {
		return Integer.toString(year)
				+ (month >= 10 ? Integer.toString(month) : "0" + month);
	}

	// how many days from 1,1 to month,date of the year
	private static int getDayCounts(int year, int month, int date) {
		if (month == 1)
			return date;
		else
			return DAY_COUNTS_OF_MONTH[month - 2] + date
					+ (month >= 3 && isRenNian(year) ? 1 : 0);
	}

	// get how many days between date1 and date2
	// getDateRange("20051112","20051114") should return 2;
	// date2 should be later then date1
	public static int getDateRange(String date1, String date2) {
		int y1 = Integer.parseInt(date1.substring(0, 4));
		int m1 = Integer.parseInt(date1.substring(4, 6));
		int d1 = Integer.parseInt(date1.substring(6, 8));

		int y2 = Integer.parseInt(date2.substring(0, 4));
		int m2 = Integer.parseInt(date2.substring(4, 6));
		int d2 = Integer.parseInt(date2.substring(6, 8));

		if (y1 == y2) {
			return getDayCounts(y2, m2, d2) - getDayCounts(y1, m1, d1);
		} else {
			int count = 0;
			for (int i = y1 + 1; i < y2; i++) {
				count += isRenNian(i) ? 366 : 365;
			}
			return count + getDayCounts(y2, m2, d2)
					+ (isRenNian(y1) ? 366 : 365) - getDayCounts(y1, m1, d1);
		}
		/*
		 * gaisuan int days = 0; days = 365*(y2-y1); days += 30*(m2-m1); days +=
		 * d2-d1; return days;
		 */
	}
	
	/**
	 * 获取ServerTime
	 * @author pp
	 */
	public static String getServerTime(){
		TimeZone zone = TimeZone.getTimeZone("GMT-00:00"); 
    	Calendar calendar = Calendar.getInstance(zone);
    	calendar.setTime(new Date());
    	DecimalFormat df = new DecimalFormat("00");
    	int iYear = calendar.get(java.util.Calendar.YEAR);
    	int iMonth = calendar.get(java.util.Calendar.MONTH) + 1;
    	int iDay = calendar.get(java.util.Calendar.DAY_OF_MONTH);
    	int iHour = calendar.get(java.util.Calendar.HOUR_OF_DAY);
    	int iMinute = calendar.get(java.util.Calendar.MINUTE);
    	int iSecond = calendar.get(java.util.Calendar.SECOND);
    	String serverTime=df.format(iYear)+""+df.format(iMonth)+""+df.format(iDay)+""+df.format(iHour)+""+df.format(iMinute)+""+df.format(iSecond);
    	return serverTime;
	}

	/**
	 * format time as HHmmssSSS
	 */
	public static String getFormatedCurrentTime9() {
		SimpleDateFormat timeSDF9 = new SimpleDateFormat("HHmmssSSS");
		return timeSDF9.format(new java.util.Date());
	}

	public static String getFormatedCurrentTime6() {
		SimpleDateFormat timeSDF6 = new SimpleDateFormat("HHmmss");
		return timeSDF6.format(new java.util.Date());
	}

	/**
	 * 获取当前时间 返回格式yyyyMMddHHmmssSSS
	 * 
	 * @return
	 */

	public static String getCurrentTime17ByMillis() {
		SimpleDateFormat timeSDF17 = new SimpleDateFormat(DATE_TIME_17);
		return timeSDF17.format(new java.util.Date());
	}

	public static String getCurrentDateTime10() {
		SimpleDateFormat timeSDF15 = new SimpleDateFormat("yyMMddHHmm");
		return timeSDF15.format(new java.util.Date());
	}
	public static String getCurrentDateTime8() {
		SimpleDateFormat timeSDF15 = new SimpleDateFormat(FORMAT_TIME_8_CHINESE);
		return timeSDF15.format(new java.util.Date());
	}
	/**
	 * 将指定格式的时间字符串格式化为yyyyMMddHHmmssSSS的时间
	 * 
	 * @param time
	 * @param formatStr
	 * @return
	 */
	public static String getTime17ByMillis(String time, String formatStr) {
		if (time == null || time.equals("")) {
			return "";
		}
		SimpleDateFormat timeSDF = new SimpleDateFormat(formatStr);
		try {
			Date date = timeSDF.parse(time);
			SimpleDateFormat timeSDF17 = new SimpleDateFormat(
					DATE_TIME_17);
			return timeSDF17.format(date);
		} catch (ParseException e) {
			throw new RuntimeException("日期格式不正确",e);
		}

	}

	/**
	 * 将yyyyMMddHHmmssSSS格式的时间字符串格式化为yyyy-MM-dd HH:mm:ss格式的时间
	 * 
	 * @param time
	 * @return
	 */
	public static String formatTime17ByMillis(String time) {
		if (time == null || time.equals("")) {
			return "";
		}
		SimpleDateFormat timeSDF17 = new SimpleDateFormat(DATE_TIME_17);
		try {
			Date date = timeSDF17.parse(time.trim());
			SimpleDateFormat dateSDF = new SimpleDateFormat(FORMAT_DATE_TIME_19);
			return dateSDF.format(date);
		} catch (ParseException e) {
			// TODO 异常处理
			throw new RuntimeException("时间格式不正确",e);
		}

	}
	/**
	 * 将yyyyMMddHHmm格式的时间字符串格式化为yyyy.MM.dd HH:mm格式的时间
	 * 
	 * @param time
	 * @return
	 */
	public static String formatTime14ByMillis(String time) {
		if (time == null || time.equals("")) {
			return "";
		}
		SimpleDateFormat timeSDF17 = new SimpleDateFormat(DATE_TIME_17);
		try {
			Date date = timeSDF17.parse(time.trim());
			SimpleDateFormat dateSDF = new SimpleDateFormat(FORMAT_DATE_TIME_16);
			return dateSDF.format(date);
		} catch (ParseException e) {
			// TODO 异常处理
			throw new RuntimeException("时间格式不正确",e);
		}

	}
	/**
	 * 将yyyyMMddHHmm格式的时间字符串格式化为yyyy.MM.dd HH:mm格式的时间
	 * 
	 * @param time
	 * @return
	 */
	public static String formatTime14ByMillis1(String time) {
		if (time == null || time.equals("")) {
			return "";
		}
		SimpleDateFormat timeSDF17 = new SimpleDateFormat(DATE_TIME_17);
		try {
			Date date = timeSDF17.parse(time.trim());
			SimpleDateFormat dateSDF = new SimpleDateFormat(FORMAT_DATE_TIME1_16);
			return dateSDF.format(date);
		} catch (ParseException e) {
			// TODO 异常处理
			throw new RuntimeException("时间格式不正确",e);
		}

	}

	/**
	 * 将yyyyMMdd格式的日期字符串转换为yyyy-MM-dd格式
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String formatDate8(String dateStr) {
		if (dateStr == null || dateStr.equals(""))
			return "";
		SimpleDateFormat dateSDF8 = new SimpleDateFormat(DATE_8);
		SimpleDateFormat dateSDF10 = new SimpleDateFormat(FORMAT_DATE_10);
		try {
			Date date = dateSDF8.parse(dateStr);
			return dateSDF10.format(date);
		} catch (ParseException e) {
			throw new RuntimeException("日期格式不正确",e);
		}
	}

	/**
	 * 将yyyy-MM-dd格式的日期字符串转换为yyyyMMdd格式
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String formatDate10(String dateStr) {
		if (dateStr == null || dateStr.equals(""))
			return "";
		SimpleDateFormat dateSDF8 = new SimpleDateFormat(DATE_8);
		SimpleDateFormat dateSDF10 = new SimpleDateFormat(FORMAT_DATE_10);
		try {
			Date date = dateSDF10.parse(dateStr);
			return dateSDF8.format(date);
		} catch (ParseException e) {
			throw new RuntimeException("日期格式不正确",e);
		}
	}
	
	
	
	/**
	 * 将yyyy-MM-dd HH:mm:ss格式的日期字符串转换为yyyyMMddHHmmssSSS格式
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String formatTime19(String dateStr) {
		if (dateStr == null || dateStr.equals(""))
			return "";
		SimpleDateFormat dateSDF17 = new SimpleDateFormat(DATE_TIME_17);
		SimpleDateFormat dateSDF19 = new SimpleDateFormat(FORMAT_DATE_TIME_19);
		try {
			Date date = dateSDF19.parse(dateStr);
			return dateSDF17.format(date);
		} catch (ParseException e) {
			throw new RuntimeException("日期格式不正确",e);
		}
	}
	
	/**
	 * 将yyyyMMddHHmmssSSS格式的日期字符串转换为yyyyMMddHHmmss格式
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String formatTime17TO14(String dateStr) {
		if (dateStr == null || dateStr.equals(""))
			return "";
		SimpleDateFormat dateSDF17 = new SimpleDateFormat(DATE_TIME_17);
		SimpleDateFormat dateSDF14 = new SimpleDateFormat(DATE_TIME_14);
		try {
			Date date = dateSDF17.parse(dateStr);
			return dateSDF14.format(date);
		} catch (ParseException e) {
			throw new RuntimeException("日期格式不正确",e);
		}
	}
	
	
	/**
	 * 将yyyy-MM-dd HH:mm:ss格式的日期字符串转换为yyyy年MM月dd日格式
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String formatTimeChinese10(String dateStr) {
		if (dateStr == null || dateStr.equals(""))
			return "";

		SimpleDateFormat dateSDF17 = new SimpleDateFormat(DATE_TIME_17);
		try {
			dateSDF17.parse(dateStr);
			String dateStr2 = dateStr.substring(0, 8);
			return getFormatedCurrentDateOfChiness(dateStr2);
		} catch (ParseException e) {
			throw new RuntimeException("日期格式不正确",e);
		}
	}

	/**
	 * 将yyyyMMddHHmmssSSS格式的时间字符串格式化为指定格式的时间字符串
	 * 
	 * @param time
	 * @param format
	 * @return
	 */
	public static String formatTime17ByMillis(String time, String format) {
		if (time == null || time.equals("")) {
			return "";
		}
		SimpleDateFormat timeSDF17 = new SimpleDateFormat(DATE_TIME_17);
		try {
			Date date = timeSDF17.parse(time);
			SimpleDateFormat dateSDF = new SimpleDateFormat(format);
			return dateSDF.format(date);
		} catch (ParseException e) {
			// TODO 异常处理
			throw new RuntimeException("时间格式不正确",e);
		}

	}

	/**
	 * 将format格式的时间转换为newFormat格式的时间
	 * 
	 * @param time
	 * @param format
	 * @param newFormat
	 * @return
	 */
	public static String format(String time, String format, String newFormat) {
		if (time == null || "".equals(time.trim())) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		SimpleDateFormat newsdf = new SimpleDateFormat(newFormat);
		try {
			return newsdf.format(sdf.parse(time));
		} catch (ParseException e) {
			//TODO 异常处理
			throw new RuntimeException("时间格式不正确",e);
		}
	}

	/**
	 * @param date
	 * @return
	 */
	public static String formatDate10ToTime17(String date) {
		return format(date, FORMAT_DATE_10, DATE_TIME_17);
	}
	
	/**
	 * @param date
	 * @return
	 */
	public static String formatDate8ToTime17(String date) {
		return format(date, DATE_8, DATE_TIME_17);
	}

	/**
	 * 获取指定日(yyyy-MM-dd)的开始时间
	 * 
	 * @return
	 */
	public static String getDateBeginTime(String date) {
		if (date == null || "".equals(date.trim())) {
			return "";
		}
		if(date.length()==8){
			return date+"000000000";
		}
		return formatDate10(date) + "000000000";
	}

	/**
	 * 获取指定日(yyyy-MM-dd)的结束时间
	 * 
	 * @return
	 */
	public static String getDateEndTime17(String date) {
		if (date == null || "".equals(date.trim())) {
			return "";
		}
		if(date.length()==8){
			return date+"235959999";
		}
		
		return formatDate10(date) + "235959999";
	}
	
	public static String getTime17ByMillis(long time) {
		SimpleDateFormat timeSDF17 = new SimpleDateFormat(DATE_TIME_17);

		return timeSDF17.format(time);
	}

	public static String getTime17ByMillis(long time, String format) {
		if (null == format || "".equals(format)) {
			return "";
		}
		SimpleDateFormat timeSDF17 = new SimpleDateFormat(format);
		return timeSDF17.format(time);
	}

	public static long getTime17ByMillis(String time) {
		SimpleDateFormat timeSDF17 = new SimpleDateFormat(DATE_TIME_17);
		long rtTime = 0;
		try {
			rtTime = timeSDF17.parse(time).getTime();
		} catch (Exception ex) {
			log.error("",ex);
		}
		return rtTime;
	}
	public static long getTime14ByMillis(String time) {
		SimpleDateFormat timeSDF14 = new SimpleDateFormat(DATE_TIME_14);
		long rtTime = 0;
		try {
			rtTime = timeSDF14.parse(time).getTime();
		} catch (Exception ex) {
			log.error("",ex);
		}
		return rtTime;
	}

	public static String getCurrentDateTime12() {
		SimpleDateFormat timeSDF12 = new SimpleDateFormat(DATE_TIME_12);
		return timeSDF12.format(new java.util.Date());
	}
	
	public static String getCurrentDateTime14() {
		SimpleDateFormat timeSDF12 = new SimpleDateFormat(DATE_TIME_14);
		return timeSDF12.format(new java.util.Date());
	}

	public static String getDateTime12(long time) {
		SimpleDateFormat timeSDF12 = new SimpleDateFormat(DATE_TIME_12);
		return timeSDF12.format(time);
	}

	public static long getMinuteRangeByMillis(long time1, long time2) {
		return (time1 - time2) / (1000 * 60);
	}

	public static String getPreYear(String date) {
		int y = Integer.parseInt(date.substring(0, 4));
		return Integer.toString(y - 1);
	}

	public static String getThisYear(String date) {
		return date.substring(0, 4);
	}

	public static String getYear(String date) {
		return date.substring(0, 4);
	}

	public static String getDate(String date) {
		return date.substring(4, 8);
	}

	public static String getNextYear(String date) {
		int y = Integer.parseInt(date.substring(0, 4));
		return Integer.toString(y + 1);
	}

	public static int getLastDateOfMonth(int year, int month) {
		if ((month == 4) || (month == 6) || (month == 9) || (month == 11)) {
			return 30;
		} else if (month == 2) {
			if (isRenNian(year))
				return 29;
			else
				return 28;
		} else {
			return 31;
		}
	}

	public static String getPreDate(String date, int count) {
		for (int i = 0; i < count; i++)
			date = getPreDate(date);
		return date;
	}

	public static String getNextDate(String date, int count) {
		for (int i = 0; i < count; i++)
			date = getNextDate(date);
		return date;
	}

	public static String getPreMonth(String month) {
		int y = Integer.parseInt(month.substring(0, 4));
		int m = Integer.parseInt(month.substring(4, 6));
		if (--m < 1) {
			m = 12;
			y--;
		}
		return getFormatedMonth(y, m);
	}

	public static String getNextMonth(String month) {
		int y = Integer.parseInt(month.substring(0, 4));
		int m = Integer.parseInt(month.substring(4, 6));
		if (++m > 12) {
			m = 1;
			y++;
		}
		return getFormatedMonth(y, m);
	}

	public static String getPreDate(String date) {
		int y = Integer.parseInt(date.substring(0, 4));
		int m = Integer.parseInt(date.substring(4, 6));
		int d = Integer.parseInt(date.substring(6, 8));

		if (d > 1)
			return getFormatedDate(y, m, d - 1);
		if ((m == 2) || (m == 4) || (m == 6) || (m == 8) || (m == 9)
				|| (m == 11)) {
			return getFormatedDate(y, m - 1, 31);
		} else if ((m == 5) || (m == 7) || (m == 10) || (m == 12)) {
			return getFormatedDate(y, m - 1, 30);
		} else if (m == 3) {
			if (isRenNian(y))
				return getFormatedDate(y, 2, 29);
			else
				return getFormatedDate(y, 2, 28);
		} else { // m == 1 and d==1
			return getFormatedDate(y - 1, 12, 31);
		}
	}

	// n should between 1 to 12
	public static String getDayOfNextNMonth(String date, int n) {
		int y = Integer.parseInt(date.substring(0, 4));
		int m = Integer.parseInt(date.substring(4, 6));
		int d = Integer.parseInt(date.substring(6, 8));
		m += n;
		if (m > 12) {
			m -= 12;
			y++;
		}
		if (d > 28) {
			if (m == 2) {
				if (isRenNian(y))
					m = 29;
				else
					m = 28;
			}
		}
		if (d == 31) {
			if ((m == 4) || (m == 6) || (m == 9) || (m == 11)) {
				d = 30;
			}
		}
		return getFormatedDate(y, m, d);
	}

	public static String getDayOfNextYear(String date) {
		int y = Integer.parseInt(date.substring(0, 4));
		int m = Integer.parseInt(date.substring(4, 6));
		int d = Integer.parseInt(date.substring(6, 8));
		if ((m == 2) && (d == 29)) {
			return getFormatedDate(y + 1, 2, 28);
		} else {
			return getFormatedDate(y + 1, m, d);
		}
	}

	public static String getNextDate(String date) {
		int y = Integer.parseInt(date.substring(0, 4));
		int m = Integer.parseInt(date.substring(4, 6));
		int d = Integer.parseInt(date.substring(6, 8));
		if (d < 28) {
			return getFormatedDate(y, m, d + 1);
		} else if (d == 31) {
			if (m == 12)
				return getFormatedDate(y + 1, 1, 1);
			else
				return getFormatedDate(y, m + 1, 1);
		} else if (d == 30) {
			if ((m == 4) || (m == 6) || (m == 9) || (m == 11)) {
				return getFormatedDate(y, m + 1, 1);
			} else {
				return getFormatedDate(y, m, 31);
			}
		} else if (d == 29) {
			if (m == 2)
				return getFormatedDate(y, 3, 1);
			else
				return getFormatedDate(y, m, 30);
		} else { // d = 28
			if (m == 2) {
				if (isRenNian(y))
					return getFormatedDate(y, m, 29);
				else
					return getFormatedDate(y, 3, 1);
			} else {
				return getFormatedDate(y, m, 29);
			}
		}
	}

	public static boolean isTimeValid(int y, int m, int d) {
		if ((y < 1000) || (y > 9999) || (m < 1) || (m > 12) || (d < 1)
				|| (d > 31))
			return false;
		if ((d == 31)
				&& ((m == 2) || (m == 4) || (m == 6) || (m == 9) || (m == 11)))
			return false;
		if (((d == 30) && (m == 2))
				|| ((d == 29) && (m == 2) && (!isRenNian(y)))) {
			return false;
		}
		return true;
	}

	public static boolean isTimeValid(int y, int m) {
		if ((y < 1000) || (y > 9999) || (m < 1) || (m > 12))
			return false;
		return true;
	}

	public static boolean isDateValid(String date) {
		try {
			if (date.length() == 8) {
				int y = Integer.parseInt(date.substring(0, 4));
				int m = Integer.parseInt(date.substring(4, 6));
				int d = Integer.parseInt(date.substring(6, 8));
				if (TimeUtil.isTimeValid(y, m, d))
					return true;
			}
		} catch (NumberFormatException ne) {
			log.error("错误信息：" , ne );
		}
		return false;
	}

	public static boolean isRenNian(int year) {
		if (year != year / 4 * 4) {
			return false;
		}
		if (year != year / 100 * 100) {
			return true;
		}
		if (year != year / 400 * 400) {
			return false;
		}
		return true;
	}

	public static long getDayStart(String datestr) {// YYYYMMDD
		int year = Integer.parseInt(datestr.substring(0, 4)) - 1900;
		int month = Integer.parseInt(datestr.substring(4, 6)) - 1;
		int date = Integer.parseInt(datestr.substring(6, 8));

		GregorianCalendar cal = new GregorianCalendar(year + 1900, month, date,
				0, 0, 0);
		/*
		 * Date d = new Date(year,month,date,0,0,0); return d.getTime();
		 */
		return cal.getTimeInMillis();
	}

	public static long getDayEnd(String datestr) {// YYYYMMDD
		int year = Integer.parseInt(datestr.substring(0, 4)) - 1900;
		int month = Integer.parseInt(datestr.substring(4, 6)) - 1;
		int date = Integer.parseInt(datestr.substring(6, 8));

		GregorianCalendar cal = new GregorianCalendar(year + 1900, month, date,
				23, 59, 60);
		return cal.getTimeInMillis();

		/*
		 * Date d = new Date(year,month,date,23,59,60); return d.getTime();
		 */
	}

	public static String getDateDisplay(String showdate, String year,
			String month, String date) {
		StringBuffer sb = new StringBuffer(16);
		sb.append(showdate.substring(0, 4)).append(year).append(
				showdate.substring(4, 6)).append(month).append(
				showdate.substring(6, 8)).append(date);
		return sb.toString();
	}

	public static String getFormatCurrentDateTime20() {
		SimpleDateFormat dateSDF = new SimpleDateFormat(FORMAT_DATE_TIME_19);
		return dateSDF.format(new java.util.Date());
	}

	public static String getFormatCurrentTime8() {
		SimpleDateFormat dateSDF = new SimpleDateFormat("HH:mm:ss");
		return dateSDF.format(new java.util.Date());
	}

	public static String getFormatCurrentDate10() {
		SimpleDateFormat dateSDF = new SimpleDateFormat(FORMAT_DATE_10);
		return dateSDF.format(new java.util.Date());
	}
	
	public static String getFormatPreMonthDate10(int mouth) {
		SimpleDateFormat dateSDF = new SimpleDateFormat(FORMAT_DATE_10);
		Calendar cal=Calendar.getInstance();    
		cal.add(Calendar.MONTH, mouth);  
		return dateSDF.format(new java.util.Date(cal.getTimeInMillis()));
	}
	
	public static String getFormatPreYearDate10(int year) {
		SimpleDateFormat dateSDF = new SimpleDateFormat(FORMAT_DATE_10);
		Calendar cal=Calendar.getInstance();    
		cal.add(Calendar.YEAR, year);  
		return dateSDF.format(new java.util.Date(cal.getTimeInMillis()));
	}
	
	public static String getFormatPreYearStartDate(int year) {
		SimpleDateFormat dateSDF = new SimpleDateFormat(FORMAT_DATE_10);
		Calendar cal=Calendar.getInstance();    
		cal.add(Calendar.YEAR, year);
		cal.add(Calendar.MONTH, 0);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		
		return dateSDF.format(new java.util.Date(cal.getTimeInMillis()));
	}
	public static String getFormatPreYearEndDate(int year) {
		SimpleDateFormat dateSDF = new SimpleDateFormat(FORMAT_DATE_10);
		Calendar cal=Calendar.getInstance();    
		cal.add(Calendar.YEAR, year);
//		  int   lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);  
//		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		return dateSDF.format(new java.util.Date(cal.getTimeInMillis()));
	}

	// public static String getFormated6CurrentDate() throws IOException,
	// ParseException {
	// String yingYeDateStr = YingYeClient.getYingYeDate();
	// return
	// getFormatedDateByFormateType(yingYeDateStr,FORMAT_yyyy_MM_dd,"yyMMdd");
	// }
	/**
	 * 根据一个日期串的原oldFormate格式转换成新的newFormate格式的日期串 dateStr 日期串 oldFormate 原日期串的格式
	 * newFormate 要返回的日期串的格式
	 */
	public static String getFormatedDateByFormateType(String dateStr,
			String oldFormate, String newFormate) throws ParseException {
		SimpleDateFormat oldDateFormat = new SimpleDateFormat(oldFormate);
		java.util.Date date = oldDateFormat.parse(dateStr);
		SimpleDateFormat dateSDF = new SimpleDateFormat(newFormate);
		return dateSDF.format(date);
	}

	public static String getPreDateBy10(String date) {
		int y = Integer.parseInt(date.substring(0, 4));
		int m = Integer.parseInt(date.substring(5, 7));
		int d = Integer.parseInt(date.substring(8, 10));

		if (d > 1)
			return getFormatedDateBy10(y, m, d - 1);
		if ((m == 2) || (m == 4) || (m == 6) || (m == 8) || (m == 9)
				|| (m == 11)) {
			return getFormatedDateBy10(y, m - 1, 31);
		} else if ((m == 5) || (m == 7) || (m == 10) || (m == 12)) {
			return getFormatedDateBy10(y, m - 1, 30);
		} else if (m == 3) {
			if (isRenNian(y))
				return getFormatedDateBy10(y, 2, 29);
			else
				return getFormatedDateBy10(y, 2, 28);
		} else { // m == 1 and d==1
			return getFormatedDateBy10(y - 1, 12, 31);
		}
	}

	public static String getNextDateBy10(String date) {
		int y = Integer.parseInt(date.substring(0, 4));
		int m = Integer.parseInt(date.substring(5, 7));
		int d = Integer.parseInt(date.substring(8, 10));

		if (d < 28) {
			return getFormatedDateBy10(y, m, d + 1);
		} else if (d == 31) {
			if (m == 12)
				return getFormatedDateBy10(y + 1, 1, 1);
			else
				return getFormatedDateBy10(y, m + 1, 1);
		} else if (d == 30) {
			if ((m == 4) || (m == 6) || (m == 9) || (m == 11)) {
				return getFormatedDateBy10(y, m + 1, 1);
			} else {
				return getFormatedDateBy10(y, m, 31);
			}
		} else if (d == 29) {
			if (m == 2)
				return getFormatedDateBy10(y, 3, 1);
			else
				return getFormatedDateBy10(y, m, 30);
		} else { // d = 28
			if (m == 2) {
				if (isRenNian(y))
					return getFormatedDateBy10(y, m, 29);
				else
					return getFormatedDateBy10(y, 3, 1);
			} else {
				return getFormatedDateBy10(y, m, 29);
			}
		}
	}

	public static String getFormatedDateBy10(int year, int month, int date) {
		return Integer.toString(year) + "-"
				+ (month >= 10 ? Integer.toString(month) : "0" + month) + "-"
				+ (date >= 10 ? Integer.toString(date) : "0" + date);
	}

	/** 返回当前日期，格式为MM-dd */
	public static String getFormatedCurrentMonthDate() {
		SimpleDateFormat dateSDF = new SimpleDateFormat("MM-dd");
		return dateSDF.format(new java.util.Date());
	}

	/**
	 * 判断是否是指定格式的日期：yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isFormatDate10(String date) {
		SimpleDateFormat SDF10 = new SimpleDateFormat(FORMAT_DATE_10);
		try {
			SDF10.parse(date);
		} catch (ParseException e) {
			return false;
		}
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(5, 7));
		int day = Integer.parseInt(date.substring(8, 10));
		if (isDate(year, month, day))
			return true;
		return false;
	}

	/**
	 * 时间是否是指定的格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateTime
	 * @return
	 */
	public static boolean isFormatDateTime19(String dateTime) {
		SimpleDateFormat SDF19 = new SimpleDateFormat(FORMAT_DATE_TIME_19);
		try {
			SDF19.parse(dateTime);
		} catch (ParseException e) {
			return false;
		}
		if (dateTime.length() != 19) {
			return false;
		}
		int year = Integer.parseInt(dateTime.substring(0, 4));
		int month = Integer.parseInt(dateTime.substring(5, 7));
		int day = Integer.parseInt(dateTime.substring(8, 10));
		int hour = Integer.parseInt(dateTime.substring(11, 13));
		int minute = Integer.parseInt(dateTime.substring(14, 16));
		int second = Integer.parseInt(dateTime.substring(17));
		return isFormatDateTime(year, month, day, hour, minute, second);
	}
	
	/**
	 * 时间是否是指定的格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateTime
	 * @return
	 */
	public static boolean isFormatDateTime17(String dateTime) {
		SimpleDateFormat SDF17 = new SimpleDateFormat(DATE_TIME_17);
		try {
			SDF17.parse(dateTime);
		} catch (ParseException e) {
			e.printStackTrace();
			//System.out.println(e.getMessage());
			return false;
		}
		if (dateTime.length() != 17) {
			return false;
		}
		int year = Integer.parseInt(dateTime.substring(0, 4));
		int month = Integer.parseInt(dateTime.substring(4, 6));
		int day = Integer.parseInt(dateTime.substring(6, 8));
		int hour = Integer.parseInt(dateTime.substring(8, 10));
		int minute = Integer.parseInt(dateTime.substring(10, 12));
		int second = Integer.parseInt(dateTime.substring(12,14));
		return isFormatDateTime(year, month, day, hour, minute, second);
	}

	public static boolean isFormatDateTime14(String dateTime) {
		try {
			new SimpleDateFormat(DATE_TIME_14).parse(dateTime);
			if (dateTime.length() != 14) {
				return false;
			}
			int year = Integer.valueOf(dateTime.substring(0, 4));
			int month = Integer.valueOf(dateTime.substring(4, 6));
			int day = Integer.valueOf(dateTime.substring(6, 8));
			int hour = Integer.valueOf(dateTime.substring(8, 10));
			int minute = Integer.valueOf(dateTime.substring(10, 12));
			int second = Integer.valueOf(dateTime.substring(13, 14));
			return isFormatDateTime(year, month, day, hour, minute, second);
		} catch (ParseException e) {
			return false;
		}
	}

	public static boolean isFormatDateTime(int year, int month, int day,
			int hour, int minute, int second) {
		return (isDate(year, month, day) && isTime(hour, minute, second));
	}

	/**
	 * 判断是否是日期
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static boolean isDate(int year, int month, int day) {
		if (year < 0 || year > 9999 || month < 1 || month > 12 || day < 1
				|| day > 31)
			return false;
		if ((day == 31)
				&& (month == 2 || month == 4 || month == 6 || month == 9 || month == 11))
			return false;
		if (day == 30 && month == 2)
			return false;
		if (day == 29 && month == 2 && !isRunNian(year))
			return false;
		return true;
	}

	/**
	 * 判断是否是时间
	 * 
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	public static boolean isTime(int hour, int minute, int second) {
		if (hour < 0 || hour > 23 || minute < 0 || minute > 59 || second < 0
				|| second > 59)
			return false;
		return true;
	}

	/**
	 * 判断是否闰年
	 * 
	 * @param year
	 * @return
	 */
	public static boolean isRunNian(int year) {
		if (year % 4 != 0)
			return false;
		if (year % 100 != 0)
			return true;
		if (year % 400 != 0)
			return false;
		return true;
	}

	public static Date nextDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}

//	public static void main(String[] args) throws ParseException {
////		 SimpleDateFormat SDF8 = new SimpleDateFormat(FORMAT_yyyy_MM_dd);
////		 SimpleDateFormat SDF17 = new
////		 SimpleDateFormat(FORMAT_yyyyMMddHHmmssSSS);
////		 System.out.println(SDF17.format(SDF8.parse("2011-09-26")));
////				
////		 System.out.println(TimeUtil.formatDate10("2011-09-26"));
////		 Date date = new Date();
////		 System.out.println(date);
////		 Calendar c = Calendar.getInstance();
////		 c.setTime(date);
////		 c.add(Calendar.DAY_OF_MONTH, 15);
////		 System.out.println(c.getTime());
////		System.out.println(new SimpleDateFormat("yyyyMMddHHmmss")
////				.parse("20120704130707"));
////		System.out.println(new SimpleDateFormat("yyyyMMddHHmmss")
////				.parse("2012070413077"));
////		
////		System.out.println(formatTime17TO14("20140123130912121"));
////		System.out.println(checkIsDate("2012-13-12"));
////		System.out.println(checkIsDate("2014-2-28"));
////		System.out.println(checkIsDate("2014-2-29"));
//		String aa ="20160216013722";
//		System.out.println(getTime14ByMillis(aa));
//	}
	
	public static boolean isTime_17(String time){
		if(time.length()!=17) return false;
		String reg = "^[0-9]*$";
		return Pattern.compile(reg).matcher(time).find();
	}
	/**
	 * 检验yyyy-MM-dd格式日期是否是合法日期
	 * @param dateStr  yyyy-MM-dd格式日期
	 * @return  是合法日期返回true，否则返回false
	 */
	public static boolean checkIsDate(String dateStr) {
		if (dateStr == null || dateStr.trim().equals(""))
			return false;
		dateStr = dateStr.trim(); 
		Date date = null;
		try {
			if (dateStr.length() <= 10) {
				date = dateFormat.parse(dateStr);
			} else{
				return false;
			}
		} catch (Exception e) {
			log.error("你输入的日期不合法，请重新输入"+dateStr,e);
			return false;
		}
		return true;
	}

	/**
	 * 获取HHmmssSSS 返回格式HH:mm:ss
	 * @return
	 */
	public static String timeFormat9To6(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_TIME_9);
		Date date;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			throw new RuntimeException("日期格式不正确", e);
		}
		SimpleDateFormat timeSDF6 = new SimpleDateFormat(FORMAT_TIME_6);
		return timeSDF6.format(date);
	}
	
	/**
	 * date 格式yyyyMMddhhmmss字符串 转换为yyyy年MM月dd日 hh:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String getFormated14Date(String dateStr) {
		SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_TIME_14);
		SimpleDateFormat dateSDF = new SimpleDateFormat(FORMAT_TIME_14_CHINESE);
		try {
			Date date = (Date) sdf1.parse(dateStr);
			return dateSDF.format(date);
		} catch (ParseException e) {
			throw new RuntimeException("日期格式不正确",e);
		}
	}
	
	/**
	 * date 格式yyyyMMdd字符串 转换为yyyy年MM月dd日
	 * 
	 * @param date
	 * @return
	 */
	public static String getFormated8Date(String dateStr) {
		SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_8);
		SimpleDateFormat dateSDF = new SimpleDateFormat(FORMAT_TIME_8_CHINESE);
		try {
			Date date = (Date) sdf1.parse(dateStr);
			return dateSDF.format(date);
		} catch (ParseException e) {
			throw new RuntimeException("日期格式不正确",e);
		}
	}
	/**
	 * @Description: 获取前一天的日志
	 * @author mxf
	 * @date 2018年10月5日 下午3:31:42
	 */
	public static String getBeforDate() {
		Date date=new Date();		
		//Calendar calendar =new GregorianCalendar();		
		Calendar calendar = Calendar.getInstance();		
		calendar.setTime(date);		
		calendar.add(calendar.DATE, -1);		
		date = calendar.getTime();		
		SimpleDateFormat format= new SimpleDateFormat("yyyyMMdd");		
		String dateString = format.format(date);		
		return dateString;
	}
	public static boolean isToday(Date inputJudgeDate) {
		boolean flag = false;
		//获取当前系统时间
		long longDate = System.currentTimeMillis();
		Date nowDate = new Date(longDate);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = dateFormat.format(nowDate);
		String subDate = format.substring(0, 10);
		//定义每天的24h时间范围
		String beginTime = subDate + " 00:00:00";
		String endTime = subDate + " 23:59:59";
		Date paseBeginTime = null;
		Date paseEndTime = null;
		try {
			paseBeginTime = dateFormat.parse(beginTime);
			paseEndTime = dateFormat.parse(endTime);
			
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		if(inputJudgeDate.after(paseBeginTime) && inputJudgeDate.before(paseEndTime)) {
			flag = true;
		}
		return flag;

}
}