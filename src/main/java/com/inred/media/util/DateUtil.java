package com.inred.media.util;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * 功能描述：
 * 
 * @author Administrator
 * @Date Jul 19, 2008
 * @Time 9:47:53 AM
 * @version 1.0
 */
public class DateUtil {

	/**
	 * 格式化im时间<br>
	 * 当日的时间显示格式为：今天 小时:分钟<br>
	 * 三天之内的时间显示格式为：昨天 小时:分钟，前天 小时:分钟<br>
	 * 三天以上一周之内的时间显示格式为：周几 小时:分钟 <br>
	 * 一周以上今年内时间显示格式为：月-日 小时:分钟<br>
	 * 早于今年显示 年后两位-月-日
	 * 
	 * @param date
	 *            待格式化的时间
	 * @return 格式化后的字符串
	 */
	public static String formatImDate(Date date) {
		Calendar now = Calendar.getInstance();
		Calendar time = Calendar.getInstance();
		time.setTime(date);
		// 如果年份数不相等
		if (now.get(Calendar.YEAR) != time.get(Calendar.YEAR)) {
			// 返回 年后两位-月-日
			return format(date, "yy-MM-dd");
		}
		// 格式化时间：小时：分钟
		String timeString = format(date, "HH:mm");
		// 获取一年中的天数
		int nowDay = now.get(Calendar.DAY_OF_YEAR);
		int timeDay = time.get(Calendar.DAY_OF_YEAR);
		// 如果是同一天
		if ((nowDay - timeDay) == 0) {
			// 返回今天 小时：分钟
			return "今天 " + timeString;
		}
		// 如果是昨天
		if ((nowDay - timeDay) == 1) {
			// 昨天 小时：分钟
			return "昨天 " + timeString;
		}
		// 如果是前天
		if ((nowDay - timeDay) == 2) {
			// 前天 小时：分钟
			return "前天 " + timeString;
		}
		// 判断是否是在同一周
		if (weekOfYeak(now.getTime(), time.getTime())) {
			// 周几 小时：分钟
			return capitalWeek(time.get(Calendar.DAY_OF_WEEK)) + timeString;
		}
		// 不再同一周返回 月-日 小时：分钟
		return format(date, "MM-dd") + " " + timeString;
	}

	/**
	 * 根据周几的数字，返回周几
	 * 
	 * @param number
	 * @return
	 */
	private static final String[] weeks = new String[] { "周六 ", "周日 ", "周一 ",
			"周二 ", "周三 ", "周四 ", "周五 ", };

	private static String capitalWeek(int number) {
		return weeks[number];
	}

	/**
	 * 功能描述：格式化日期
	 * 
	 * @param dateStr
	 *            String 字符型日期
	 * @param format
	 *            String 格式
	 * @return Date 日期
	 */
	public static Date parseDate(String dateStr, String format) {
		Date date = null;
		try {
			SimpleDateFormat format2 = new SimpleDateFormat(format);
			date = format2.parse(dateStr);
		} catch (Exception e) {
		}
		return date;
	}

	/**
	 * 功能描述：格式化日期
	 * 
	 * @param dateStr
	 *            String 字符型日期：YYYY-MM-DD 格式
	 * @return Date
	 */
	public static Date parseDate(String dateStr) {
		return parseDate(dateStr, "yyyy-MM-dd");
	}

	/**
	 * 功能描述：格式化输出日期
	 * 
	 * @param date
	 *            Date 日期
	 * @param format
	 *            String 格式
	 * @return 返回字符型日期
	 */
	public static String format(Date date, String format) {
		String result = "";
		try {
			if (date != null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat(format);
				result = dateFormat.format(date);
			}
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 返回这个时间是本年第多少周
	 * 
	 * @param date
	 * @return
	 */
	public static int getYearOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 返回这个时间是本年第多少周
	 * 
	 * @param date
	 * @return
	 */
	public static int getYearOfWeeks(Date date) {
		Date dates = addDate(date, 2);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dates);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 判断两个日期是不是一个周
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean weekOfYeak(Date date1, Date date2) {
		String dates1 = getMondayOfThisWeek(date1);
		String dates2 = getMondayOfThisWeek(date2);
		if (dates1.equals(dates2)) {
			return true;
		}
		return false;
	}

	/**
	 * 返回这个时间是本年第多少天
	 * 
	 * @param date
	 * @return
	 */
	public static int getYearOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 获取给定时间的下一个时段
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNextTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// 获取一天中的小时数
		int nowHour = calendar.get(Calendar.HOUR_OF_DAY);
		// 判断当前小时数在哪个时段
		if (nowHour > 9 && nowHour <= 12) {
			calendar.set(Calendar.HOUR_OF_DAY, 17);

		} else if (nowHour > 12 && nowHour <= 18) {
			calendar.set(Calendar.HOUR_OF_DAY, 8);
		} else {
			calendar.set(Calendar.HOUR_OF_DAY, 11);
			// 加一天
			calendar.add(Calendar.DAY_OF_YEAR, 1);
		}
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();

	}

	/**
	 * 获取给定时间的下一个时段到12点
	 * 
	 * @param date
	 * @return
	 */
	public static Date getHoursNextTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// 获取一天中的小时数
		int nowHour = calendar.get(Calendar.HOUR_OF_DAY);
		// 判断当前小时数在哪个时段
		if (nowHour < 12) {
			calendar.set(Calendar.HOUR_OF_DAY, 11);
			calendar.add(Calendar.DAY_OF_YEAR, 1);
		} else {
			calendar.set(Calendar.HOUR_OF_DAY, 11);
			// 加一天
			calendar.add(Calendar.DAY_OF_YEAR, 2);
		}
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();

	}

	/**
	 * 获取当前时间所在的时段结尾
	 * 
	 * @return
	 */
	public static Date getNowTime() {
		Calendar calendar = Calendar.getInstance();
		// 获取一天中的小时数
		int nowHour = calendar.get(Calendar.HOUR_OF_DAY);
		// 判断当前小时数在哪个时段
		if (nowHour > 9 && nowHour <= 12) {
			calendar.set(Calendar.HOUR_OF_DAY, 11);

		} else if (nowHour > 12 && nowHour <= 18) {
			calendar.set(Calendar.HOUR_OF_DAY, 17);
		} else {
			calendar.set(Calendar.HOUR_OF_DAY, 8);
			// 加一天
			calendar.add(Calendar.DAY_OF_YEAR, 1);
		}
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 获取当前时间所在的时段结尾到12点
	 * 
	 * @return
	 */
	public static Date getHoursNowTime() {
		Calendar calendar = Calendar.getInstance();
		// 获取一天中的小时数
		int nowHour = calendar.get(Calendar.HOUR_OF_DAY);
		// 判断当前小时数在哪个时段
		if (nowHour < 12) {
			calendar.set(Calendar.HOUR_OF_DAY, 11);

		} else {
			calendar.set(Calendar.HOUR_OF_DAY, 11);
			// 加一天
			calendar.add(Calendar.DAY_OF_YEAR, 1);
		}
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 功能描述：
	 * 
	 * @param date
	 *            Date 日期
	 * @return
	 */
	public static String format(Date date) {
		return format(date, "yyyy-MM-dd");
	}

	/**
	 * 功能描述：返回年份
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回年份
	 */
	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}
	

	/**
	 * 功能描述：返回月份
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回月份
	 */
	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 功能描述：返回日期
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回日期
	 */
	public static int getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 根据当前时间获得距离下个时间段的间隔
	 * 
	 * @return
	 */
	public static int getChachTime() {
		Calendar calendar = Calendar.getInstance();
		// 获取一天中的小时数
		int nowHour = calendar.get(Calendar.HOUR_OF_DAY);
		// 判断当前小时数在哪个时段
		if (nowHour >= 9 && nowHour < 12) {
			calendar.set(Calendar.HOUR_OF_DAY, 12);
		} else if (nowHour >= 12 && nowHour < 18) {
			calendar.set(Calendar.HOUR_OF_DAY, 18);
		} else {
			if (nowHour > 18) {
				calendar.add(Calendar.DAY_OF_YEAR, 1);
			}
			calendar.set(Calendar.HOUR_OF_DAY, 9);
		}
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return (int) (calendar.getTime().getTime() - System.currentTimeMillis());
	}

	/**
	 * 距离12点的秒数
	 * 
	 * @return
	 */
	public static int gethourTime() {
		Calendar calendar = Calendar.getInstance();
		// 获取一天中的小时数
		int nowHour = calendar.get(Calendar.HOUR_OF_DAY);
		if (nowHour < 12) {
			calendar.set(Calendar.HOUR_OF_DAY, 12);
		} else {
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			calendar.set(Calendar.HOUR_OF_DAY, 12);
		}
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return (int) ((calendar.getTime().getTime() - System
				.currentTimeMillis()) / 1000);
	}

	/**
	 * 根据当前时间获得当前时段的起始时间
	 * 
	 * @return
	 */
	public static String getStartTime() {
		Calendar calendar = Calendar.getInstance();
		// 获取一天中的小时数
		int nowHour = calendar.get(Calendar.HOUR_OF_DAY);
		// 判断当前小时数在哪个时段
		if (nowHour > 9 && nowHour <= 12) {
			calendar.set(Calendar.HOUR_OF_DAY, 18);
			// 减一天
			calendar.add(Calendar.DAY_OF_YEAR, -1);
		} else if (nowHour > 12 && nowHour <= 18) {
			calendar.set(Calendar.HOUR_OF_DAY, 9);
		} else {
			calendar.set(Calendar.HOUR_OF_DAY, 12);
		}
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return format(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 根据当前时间获得当前时段的起始时间到12点
	 * 
	 * @return
	 */
	public static String getHourStartTime() {
		Calendar calendar = Calendar.getInstance();
		// 获取一天中的小时数
		int nowHour = calendar.get(Calendar.HOUR_OF_DAY);
		// 判断当前小时数在哪个时段
		if (nowHour < 12) {
			calendar.set(Calendar.HOUR_OF_DAY, 11);
			calendar.add(Calendar.DAY_OF_YEAR, -2);
		} else {
			calendar.set(Calendar.HOUR_OF_DAY, 11);
			calendar.add(Calendar.DAY_OF_YEAR, -1);
		}
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return format(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 根据当前时间获得当前时段的结束时间
	 * 
	 * @return
	 */
	public static String getEndTime() {
		Calendar calendar = Calendar.getInstance();
		// 获取一天中的小时数
		int nowHour = calendar.get(Calendar.HOUR_OF_DAY);
		// 判断当前小时数在哪个时段
		if (nowHour > 9 && nowHour <= 12) {
			calendar.set(Calendar.HOUR_OF_DAY, 8);

		} else if (nowHour > 12 && nowHour <= 18) {
			calendar.set(Calendar.HOUR_OF_DAY, 11);
		} else {
			calendar.set(Calendar.HOUR_OF_DAY, 17);
		}
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return format(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");

	}

	/**
	 * 根据当前时间获得当前时段的结束时间到12点
	 * 
	 * @return
	 */
	public static String getHoursEndTime() {
		Calendar calendar = Calendar.getInstance();
		// 获取一天中的小时数
		int nowHour = calendar.get(Calendar.HOUR_OF_DAY);
		// 判断当前小时数在哪个时段
		if (nowHour < 12) {
			calendar.set(Calendar.HOUR_OF_DAY, 11);
			calendar.add(Calendar.DAY_OF_YEAR, -1);
		} else {
			calendar.set(Calendar.HOUR_OF_DAY, 11);
		}
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return format(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");

	}

	/**
	 * 根据当前时间获得当前时段的结束时间到12点
	 * 
	 * @return
	 */
	public static Date getDateEndTime() {
		Calendar calendar = Calendar.getInstance();
		// 获取一天中的小时数
		int nowHour = calendar.get(Calendar.HOUR_OF_DAY);
		// 判断当前小时数在哪个时段
		if (nowHour < 12) {
			calendar.set(Calendar.HOUR_OF_DAY, 12);
			calendar.add(Calendar.DAY_OF_YEAR, -1);
		} else {
			calendar.set(Calendar.HOUR_OF_DAY, 12);
		}
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();

	}

	/**
	 * 判断两个时间的中间查了多少天
	 * 
	 * @param date1
	 *            大的时间
	 * @param date2
	 *            小的时间
	 * @return
	 */
	public static int getDays(Date date1, Date date2) {
		if ((getMillis(date1) - getMillis(date2)) % (1000 * 60 * 60 * 24) == 0) {
			return (int) (getMillis(date1) - getMillis(date2))
					/ (1000 * 60 * 60 * 24);
		}
		return (int) (getMillis(date1) - getMillis(date2))
				/ (1000 * 60 * 60 * 24) + 1;
	}

	/**
	 * 判断两个时间是不是同一天 是同一天返回true
	 */
	public static boolean isOneDay(Date date1, Date date2) {
		String d1 = format(date1, "yyyyMMdd");
		String d2 = format(date2, "yyyyMMdd");
		if (d1.equals(d2)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断第二个时间是不是第一个时间的昨天
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isYestday(Date date1, Date date2) {
		// 第二个时间减去一天
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date2);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		// 判断如果和第一个时间是同一天则为昨天
		return isOneDay(date1, calendar.getTime());
	}

	/**
	 * 获取昨天的0时0分0秒
	 * 
	 * @param date
	 * @return
	 */
	public static Date getYestday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取今天的0时0分0秒
	 * 
	 * @param date
	 * @return
	 */
	public static Date getToday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取昨天的23时59分59秒
	 * 
	 * @param date
	 * @return
	 */
	public static Date getYestday1(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 判断两个日期是不是一天
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isDays(Date date1, Date date2) {
		long a1 = getMillis(date1) / (1000 * 60 * 60 * 24);
		long a2 = getMillis(date2) / (1000 * 60 * 60 * 24);
		return a1 == a2;
	}

	/**
	 * 功能描述：返回小时
	 * 
	 * @param date
	 *            日期
	 * @return 返回小时
	 */
	public static int getHour(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 功能描述：返回分钟
	 * 
	 * @param date
	 *            日期
	 * @return 返回分钟
	 */
	public static int getMinute(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * 返回秒钟
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回秒钟
	 */
	public static int getSecond(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * 功能描述：返回毫秒
	 * 
	 * @param date
	 *            日期
	 * @return 返回毫秒
	 */
	public static long getMillis(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getTimeInMillis();
	}

	/**
	 * 功能描述：返回字符型日期
	 * 
	 * @param date
	 *            日期
	 * @return 返回字符型日期 yyyy/MM/dd 格式
	 */
	public static String getDate(Date date) {
		return format(date, "yyyy/MM/dd");
	}

	/**
	 * 功能描述：返回字符型时间
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回字符型时间 HH:mm:ss 格式
	 */
	public static String getTime(Date date) {
		return format(date, "HH:mm:ss");
	}

	/**
	 * 功能描述：返回字符型日期时间
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回字符型日期时间 yyyy/MM/dd HH:mm:ss 格式
	 */
	public static String getDateTime(Date date) {
		return format(date, "yyyy/MM/dd HH:mm:ss");
	}

	/**
	 * 功能描述：返回字符型日期时间
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回字符型日期时间 yyyy/MM/dd HH:mm:ss 格式
	 */
	public static String getDateTimes(Date date) {
		return format(date, "yyyy/MM/dd");
	}

	/**
	 * 功能描述：返回字符型日期时间
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回字符型日期时间 yyyy/MM/dd HH:mm:ss 格式
	 */
	public static String getTimes(Date date) {
		return format(date, "yyyy-MM-dd");
	}

	/**
	 * 功能描述：返回没有空格的日期时间
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回字符型日期时间 yyyy/MM/dd HH:mm:ss 格式
	 */
	public static String getDateTimeTrim(Date date) {
		return format(date, "yyyyMMddHHmmss");
	}

	/**
	 * 功能描述：日期相加
	 * 
	 * @param date
	 *            Date 日期
	 * @param day
	 *            int 天数
	 * @return 返回相加后的日期/
	 */
	public static Date addDate(Date date, int day) {
		Calendar calendar = Calendar.getInstance();
		long millis = getMillis(date) + ((long) day) * 24 * 3600 * 1000;
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

	/**
	 * 功能描述：日期相减
	 * 
	 * @param date
	 *            Date 日期
	 * @param date1
	 *            Date 日期
	 * @return 返回相减后的日期
	 */
	public static int diffDate(Date date, Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
	}

	/**
	 * 功能描述：日期相减
	 * 
	 * @param date
	 *            Date 日期
	 * @param date1
	 *            Date 日期
	 * @return 返回相减后的日期
	 */
	public static long diffDateLong(Date date, Date date1) {
		return (getMillis(date) - getMillis(date1));
	}

	/**
	 * 返回两个时间相差多少月
	 * 
	 * @param date
	 *            被减时间
	 * @param date2
	 *            减掉时间
	 * @return
	 */
	public static int diffMonth(Date date, Date date2) {
		int a = getYear(date);
		int b = getYear(date2);
		int c = getMonth(date);
		int startMonth = (a - b) * 12 + c;
		int endMonth = getMonth(date2);
		return startMonth - endMonth;

	}

	/**
	 * 功能描述：取得指定月份的第一天
	 * 
	 * @param strdate
	 *            String 字符型日期
	 * @return String yyyy-MM-dd 格式
	 */
	public static String getMonthBegin(String strdate) {
		Date date = parseDate(strdate);
		return format(date, "yyyy-MM") + "-01";
	}

	/**
	 * 功能描述：取得指定月份的第一天
	 * 
	 * @param strdate
	 *            String 字符型日期
	 * @return String yyyy-MM-dd 格式
	 */
	public static String getMonthBegin(Date date) {
		return format(date, "yyyy-MM") + "-01";
	}

	/**
	 * 取得下个月的第二天
	 * 
	 * @param date
	 * @return
	 */
	public static String getNextMouthSecondDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		return format(calendar.getTime(), "yyyy-MM") + "-02";
	}

	/**
	 * 功能描述：取得指定月份的第一天
	 * 
	 * @param strdate
	 *            String 字符型日期
	 * @return String yyyy-MM-dd 格式
	 */
	public static Date getMonthBegin1(Date date) {
		String a = format(date, "yyyy-MM") + "-01";
		return parseDate(a, "yyyy-MM-dd");
	}

	/**
	 * 功能描述：取得指定月份的最后一天
	 * 
	 * @param strdate
	 *            String 字符型日期
	 * @return String 日期字符串 yyyy-MM-dd格式
	 */
	public static String getMonthEnd(String strdate) {
		Date date = parseDate(getMonthBegin(strdate));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return formatDate(calendar.getTime());
	}

	/**
	 * 取得当前时间之前或之后的几个小时的时间
	 * 
	 * @param date
	 * @param hour
	 * @return
	 */
	public static Date getHour(Date date, int hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, hour);
		return calendar.getTime();
	}

	/**
	 * 功能描述：取得指定月份的最后一天
	 * 
	 * @param strdate
	 *            String 字符型日期
	 * @return String 日期字符串 yyyy-MM-dd格式
	 */
	public static Date getMonthEnd(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return calendar.getTime();
	}

	/**
	 * 获取一个时间所在周的周几
	 * 
	 * @param date
	 * @param CalendatDate
	 *            calendat日期静态常量如：Calendar.MONDAY
	 * @param format
	 *            要格式化的字符串
	 * @return
	 */
	// public static String getWeekDay(Date date, int CalendatDate, String
	// format) {
	// calendar = Calendar.getInstance();
	// calendar.setTime(date);
	// calendar.set(Calendar.DAY_OF_WEEK, CalendatDate);
	// return format(calendar.getTime(), format);
	// }
	/**
	 * 得到本周周一
	 * 
	 * @return yyyy-MM-dd
	 */
	public static String getMondayOfThisWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0)
			dayofweek = 7;
		c.add(Calendar.DATE, -dayofweek + 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(c.getTime());
	}

	/**
	 * 得到本周周一
	 * 
	 * @return yyyy-MM-dd
	 */
	public static Date getMondayOfThisWeeks(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0)
			dayofweek = 7;
		c.add(Calendar.DATE, -dayofweek + 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	/**
	 * 计算时间加上月份后的时间
	 * 
	 * @param date
	 * @param mouth
	 * @return
	 */
	public static Date addMouth(Date date, Integer mouth) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, mouth);
		return calendar.getTime();
	}

	/**
	 * 功能描述：常用的格式化日期
	 * 
	 * @param date
	 *            Date 日期
	 * @return String 日期字符串 yyyy-MM-dd格式
	 */
	public static String formatDate(Date date) {
		return formatDateByFormat(date, "yyyy-MM-dd");
	}

	/**
	 * 功能描述：以指定的格式来格式化日期
	 * 
	 * @param date
	 *            Date 日期
	 * @param format
	 *            String 格式
	 * @return String 日期字符串
	 */
	public static String formatDateByFormat(Date date, String format) {
		String result = "";
		if (date != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				result = sdf.format(date);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 根据年龄计算出生年份
	 * 
	 * @param age
	 * @return
	 */
	public static int getBrithdayYear(int age) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.YEAR, -age);
		int year = getYear(calendar.getTime());

		return year;
	}

	/**
	 * 根据生日计算周岁年龄
	 * 
	 * @param month
	 * @param day
	 * @param year
	 * @return
	 * @throws Exception
	 */
	public static int getDuration(Date date) {
		int month = getMonth(date);
		int day = getDay(date);
		int year = getYear(date);
		month--; // following the 0-based rule
		Calendar cal = new GregorianCalendar(year, month, day);
		java.util.Date today = new java.util.Date();
		int intYear = getYear(today);
		int intMonth = getMonth(today);
		intMonth--; // following the 0-based rule
		int intDay = getDay((today));
		Calendar now = new GregorianCalendar(intYear, intMonth, intDay);

		int yyyy = intYear - year;

		int[] months = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		int factor = 0;

		int mm = 0; // month duration

		int dd = 0; // day duration

		if ((month > intMonth) || (month == intMonth && day > intDay)) {
			factor = -1;
			yyyy += factor;
		}

		if (month > 12) {
			return 0;
		} else if (day > months[month]) {
			return 0;
		} else if (yyyy < 0) {
			return 0;
		}

		if (factor == 0) {
			// compute for days in between the given and the current date
			dd = now.get(Calendar.DAY_OF_YEAR) - cal.get(Calendar.DAY_OF_YEAR);

			if ((intYear % 4) == 0) {
				months[1]++; // increment the days in February by 1
			}

			for (int i = month; i <= intMonth; i++) {
				if (dd >= months[i]) {
					dd -= months[i];
					mm++;
				}
			}

			if (mm >= 12) {
				yyyy += (mm / 12);
				mm %= 12;
			}
		} else { // if the given date is greater than the current date
			intYear--; // derive previous year

			Calendar prev = new GregorianCalendar(intYear, 11, 31);

			dd = (prev.get(Calendar.DAY_OF_YEAR) - cal
					.get(Calendar.DAY_OF_YEAR)) + now.get(Calendar.DAY_OF_YEAR);

			if ((intYear % 4) == 0) {
				months[1]++; // increment the days in February by 1
			}

			for (int i = month; i <= 11; i++) {
				if (dd >= months[i]) {
					dd -= months[i];
					mm++;
				}
			}
			intYear++; // set the value back to the current year

			if ((intYear % 4) == 0) {
				months[1]++; // increment the days in February by 1
			}

			for (int i = 0; i <= intMonth; i++) {
				if (dd >= months[i]) {
					dd -= months[i];
					mm++;
				}
			}

			if (mm >= 12) {
				yyyy += (mm / 12);
				mm %= 12;
			}
		}

		return yyyy;
	}

	public static int getMonthLastDay(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 获取一个时间距当前24点所差的秒数
	 * 
	 * @param date
	 * @return
	 */
	public static int getTo24Second(Date date) {
		int start = (int) (date.getTime() / 1000);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date endDate = calendar.getTime();
		endDate = parseDate(format(endDate, "yyyy-MM-dd"), "yyyy-MM-dd");
		int end = (int) (endDate.getTime() / 1000);
		return end - start;
	}
	
	/**
	 * 获取当前时间的yyyyMMdd格式
	 * @return
	 */
	public static String getNowFormat(){
		return format(new Date(), "yyyyMMdd");
	}
	public static void main(String[] args) {
		System.out.println(getYear(new Date()));
	}

}
