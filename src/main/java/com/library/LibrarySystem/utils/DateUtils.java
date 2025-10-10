package com.library.LibrarySystem.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {
    
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    private static final String DISPLAY_DATE_FORMAT = "dd/MM/yyyy";
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DISPLAY_DATETIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
    

    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        return sdf.format(date);
    }
    
    /**
     * Formats a date to string using display format (dd/MM/yyyy)
     * @param date The date to format
     * @return Formatted date string
     */
    public static String formatDateForDisplay(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DISPLAY_DATE_FORMAT);
        return sdf.format(date);
    }
    
    /**
     * Formats a date to string with time using default format
     * @param date The date to format
     * @return Formatted datetime string
     */
    public static String formatDateTime(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
        return sdf.format(date);
    }
    
    /**
     * Formats a date to string with time for display
     * @param date The date to format
     * @return Formatted datetime string
     */
    public static String formatDateTimeForDisplay(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DISPLAY_DATETIME_FORMAT);
        return sdf.format(date);
    }
    
    /**
     * Parses a date string to Date object
     * @param dateStr The date string to parse
     * @param format The format of the date string
     * @return Date object or null if parsing fails
     */
    public static Date parseDate(String dateStr, String format) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.setLenient(false);
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }
    
    /**
     * Parses a date string using default format (yyyy-MM-dd)
     * @param dateStr The date string to parse
     * @return Date object or null if parsing fails
     */
    public static Date parseDate(String dateStr) {
        return parseDate(dateStr, DEFAULT_DATE_FORMAT);
    }
    
    /**
     * Adds days to a date
     * @param date The base date
     * @param days Number of days to add (can be negative)
     * @return New date with added days
     */
    public static Date addDays(Date date, int days) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }
    
    /**
     * Adds months to a date
     * @param date The base date
     * @param months Number of months to add (can be negative)
     * @return New date with added months
     */
    public static Date addMonths(Date date, int months) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
    }
    
    /**
     * Adds years to a date
     * @param date The base date
     * @param years Number of years to add (can be negative)
     * @return New date with added years
     */
    public static Date addYears(Date date, int years) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, years);
        return calendar.getTime();
    }
    
    /**
     * Calculates the difference in days between two dates
     * @param date1 First date
     * @param date2 Second date
     * @return Number of days between dates
     */
    public static long getDaysBetween(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }
        long diffInMillies = Math.abs(date2.getTime() - date1.getTime());
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }
    
    /**
     * Checks if a date is after another date
     * @param date1 First date
     * @param date2 Second date
     * @return true if date1 is after date2
     */
    public static boolean isAfter(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        return date1.after(date2);
    }
    
    /**
     * Checks if a date is before another date
     * @param date1 First date
     * @param date2 Second date
     * @return true if date1 is before date2
     */
    public static boolean isBefore(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        return date1.before(date2);
    }
    
    /**
     * Checks if a date is today
     * @param date The date to check
     * @return true if date is today
     */
    public static boolean isToday(Date date) {
        if (date == null) {
            return false;
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date);
        
        Calendar cal2 = Calendar.getInstance();
        
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
               cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }
    
    /**
     * Gets the current date without time
     * @return Current date with time set to 00:00:00
     */
    public static Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
    
    /**
     * Gets the current date and time
     * @return Current date and time
     */
    public static Date getCurrentDateTime() {
        return new Date();
    }
    
    /**
     * Resets time portion of a date to 00:00:00
     * @param date The date to reset
     * @return Date with time set to 00:00:00
     */
    public static Date resetTime(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
    
    /**
     * Gets the start of the month for a given date
     * @param date The date
     * @return First day of the month
     */
    public static Date getStartOfMonth(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return resetTime(calendar.getTime());
    }
    
    /**
     * Gets the end of the month for a given date
     * @param date The date
     * @return Last day of the month
     */
    public static Date getEndOfMonth(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return resetTime(calendar.getTime());
    }
    
    /**
     * Checks if a date is overdue compared to current date
     * @param dueDate The due date to check
     * @return true if the date is in the past
     */
    public static boolean isOverdue(Date dueDate) {
        if (dueDate == null) {
            return false;
        }
        return new Date().after(dueDate);
    }
    
    /**
     * Gets a human-readable string representing the time difference
     * @param pastDate The past date
     * @return Human-readable time difference (e.g., "2 days ago")
     */
    public static String getTimeAgo(Date pastDate) {
        if (pastDate == null) {
            return "";
        }
        
        long diffInMillies = new Date().getTime() - pastDate.getTime();
        long seconds = TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        long minutes = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
        long hours = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        long days = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        
        if (days > 0) {
            return days + (days == 1 ? " day ago" : " days ago");
        } else if (hours > 0) {
            return hours + (hours == 1 ? " hour ago" : " hours ago");
        } else if (minutes > 0) {
            return minutes + (minutes == 1 ? " minute ago" : " minutes ago");
        } else {
            return seconds + (seconds == 1 ? " second ago" : " seconds ago");
        }
    }
}
