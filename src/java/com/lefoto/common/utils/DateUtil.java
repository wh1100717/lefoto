/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.common.utils;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Eric
 */
public class DateUtil {

    public static int getYear() {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.YEAR);
    }

    public static int getMonth() {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.MONTH) + 1;
    }

    public static int getDayOfMonth() {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.DAY_OF_MONTH);
    }

    public static int getHourOfDay() {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute() {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.MINUTE);
    }

    public static int getSecond() {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.SECOND);
    }

    public static long getTimeInMillis() {
        Calendar now = Calendar.getInstance();
        return now.getTimeInMillis();
    }

    public static Date getTime() {
        Calendar now = Calendar.getInstance();
        return now.getTime();
    }
}
