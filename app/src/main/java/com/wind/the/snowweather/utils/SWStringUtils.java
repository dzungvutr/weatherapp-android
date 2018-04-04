package com.wind.the.snowweather.utils;

import com.wind.the.snowweather.model.HoursBackground;
import com.wind.the.snowweather.model.WeatherCode;
import com.wind.the.snowweather.model.weather.CountryCode;

import java.text.DecimalFormat;

/**
 * Created by DzungVt on 2/28/2018.
 */


/**
 * Convert state weather to icon URL
 * @param
 * @return URL icon weather
 *
 * */
public final class SWStringUtils {
    public static int convertStateInt(String code) {
        for (WeatherCode weatherCode : WeatherCode.values()) {
            if (weatherCode.getCode().equals(code)) {
                return weatherCode.getStateURL();
            }
        }
        return 0;
    }

    /**
     * Convert state weather daily to background URL
     * @param
     * @return URL icon daily
     *
     * */
    public static int convertBackgroundDailyInt(String code) {
        for (WeatherCode weatherCode : WeatherCode.values()) {
            if (weatherCode.getCode().equals(code)) {
                return weatherCode.getDailyBackgroundURL();
            }
        }
        return 0;
    }

    /**
     * Convert state weather today to icon URL
     * @param
     * @return URL icon today
     *
     * */
    public static int convertBackGroundMainInt(String code) {
        for (WeatherCode weatherCode : WeatherCode.values()) {
            if (weatherCode.getCode().equals(code)) {
                return weatherCode.getMainBackGroundURL();
            }
        }
        return 0;
    }

    /**
     * Convert state weather hours to icon URL
     * @param
     * @return URL icon hours
     *
     * */
    public static int convertBackgroundHoursInt(String time) {
        time = time.substring(0, time.indexOf(":"));
        if (Integer.parseInt(time) > 0) {
            if (Integer.parseInt(time) <= 12) {
                return HoursBackground.MORNING.getURL();
            } else if (Integer.parseInt(time) <= 18) {
                return HoursBackground.AFTERNOON.getURL();
            } else if (Integer.parseInt(time) <= 23) {
                return HoursBackground.EVENING.getURL();
            }
        }
        return HoursBackground.EVENING.getURL();
    }

    /**
     * Sub hours
     * @param hours hours
     * @return URL
     *
     * */
    public static String subHours(String hours) {
        return hours = hours.substring(hours.indexOf(" ") + 1, hours.indexOf(":") + 3);
    }

    /**
     * Convert metre to kilometer
     * @param visibility
     * @return kilometer
     *
     * */

    public static String convertMetreToKilometer(Float visibility) {
        if (visibility == null) {
            return "";
        }
        return new DecimalFormat("#.##").format(visibility / 1000f);
    }

    /**
     * Convert A2code to country name
     * @param code
     * @return country name
     *
     * */
    public static String convertA2CodeToCountry(String code) {
        for (CountryCode countryCode : CountryCode.values()) {
            if (countryCode.getCountryCodeA2().equals(code)) {
                return countryCode.getCountryName();
            }
        }
        return code;
    }
}


