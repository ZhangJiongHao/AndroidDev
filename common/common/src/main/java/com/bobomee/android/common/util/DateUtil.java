package com.bobomee.android.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created on 16/7/10.上午12:27.
 *
 * @author bobomee.
 *         wbwjx115@gmail.com
 */
public class DateUtil {
  private static final SimpleDateFormat DATE_FORMAT_DATETIME =
      new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  private static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");
  private static final SimpleDateFormat DATE_FORMAT_TIME = new SimpleDateFormat("HH:mm:ss");

  public static String formatDataTime(long date) {
    return DATE_FORMAT_DATETIME.format(new Date(date));
  }

  public static String formatDate(long date) {
    return DATE_FORMAT_DATE.format(new Date(date));
  }

  public static String formatTime(long date) {
    return DATE_FORMAT_TIME.format(new Date(date));
  }

  public static String formatDateCustom(String beginDate, String format) {
    return new SimpleDateFormat(format).format(new Date(Long.parseLong(beginDate)));
  }

  public static String formatDateCustom(Date beginDate, String format) {
    return new SimpleDateFormat(format).format(beginDate);
  }

  public static Date string2Date(String s, String style) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
    simpleDateFormat.applyPattern(style);
    Date date = null;
    if (s == null || s.length() < 6) {
      return null;
    }
    try {
      date = simpleDateFormat.parse(s);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return date;
  }

  public static String getTime() {
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(System.currentTimeMillis());
    return cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(
        Calendar.SECOND);
  }

  public static long subtractDate(Date dateStart, Date dateEnd) {
    return dateEnd.getTime() - dateStart.getTime();
  }

  public static Date getDateAfter(Date d, int day) {
    Calendar now = Calendar.getInstance();
    now.setTime(d);
    now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
    return now.getTime();
  }

  public static int getWeekOfMonth() {
    Calendar calendar = Calendar.getInstance();
    int week = calendar.get(Calendar.WEEK_OF_MONTH);
    return week - 1;
  }

  public static int getDayOfWeek() {
    Calendar calendar = Calendar.getInstance();
    int day = calendar.get(Calendar.DAY_OF_WEEK);
    if (day == 1) {
      day = 7;
    } else {
      day = day - 1;
    }
    return day;
  }

  public static String toDate(Date date, DateFormat dateFormat) {
    return dateFormat.format(date);
  }

  public static String toDate(Date date, int add, DateFormat dateFormat) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.DATE, add);
    return toDate(calendar.getTime(), dateFormat);
  }

  public static Date getLastdayDate(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.DATE, -1);
    return calendar.getTime();
  }

  public static Date getNextdayDate(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.DATE, 1);
    return calendar.getTime();
  }

  public static boolean isTheSameDay(Date one, Date another) {
    Calendar _one = Calendar.getInstance();
    _one.setTime(one);
    Calendar _another = Calendar.getInstance();
    _another.setTime(another);
    int oneDay = _one.get(Calendar.DAY_OF_YEAR);
    int anotherDay = _another.get(Calendar.DAY_OF_YEAR);

    return oneDay == anotherDay;
  }
}
