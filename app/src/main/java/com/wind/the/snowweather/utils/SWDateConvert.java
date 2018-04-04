package com.wind.the.snowweather.utils;

/**
 * Created by DzungVt on 2/15/2018.
 */

import java.text.SimpleDateFormat;
import java.util.Date;

public final class SWDateConvert {
    public static String convertDate(Integer dt) {
        Long datetime = Long.valueOf(dt);
        Date date = new Date(datetime * 1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateFormat = simpleDateFormat.format(date);
        return dateFormat;
    }

    public static String convertDay(Integer dt) {
        Long datetime = Long.valueOf(dt);
        Date date = new Date(datetime * 1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        String dateFormat = simpleDateFormat.format(date);
        return dateFormat;
    }

    public static String convertDayDateYear(Integer dt) {
        Long datetime = Long.valueOf(dt);
        Date date = new Date(datetime * 1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE yyyy-MM-dd");
        String dateFormat = simpleDateFormat.format(date);
        return dateFormat;
    }

    public static String convertDayDate(Integer dt) {
        Long datetime = Long.valueOf(dt);
        Date date = new Date(datetime * 1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE MM-dd");
        String dateFormat = simpleDateFormat.format(date);
        return dateFormat;
    }

    public static String convertHours(Integer dt) {
        Long datetime = Long.valueOf(dt);
        Date date = new Date(datetime * 1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("H:mm");
        String hoursFormat = simpleDateFormat.format(date);
        return hoursFormat;
    }


}
