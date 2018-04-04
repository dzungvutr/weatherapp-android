package com.wind.the.snowweather.model;

import com.wind.the.snowweather.R;

/**
 * Created by DzungVt on 2/27/2018.
 * STATE("String state", "String code", "String URL")
 */

public enum WeatherCode {
    CLEAR_SKY_D("clear sky", "01d", R.drawable.ic_clear_day, R.drawable.h_sunny_day, R.drawable.day_clearsky),
    FEW_CLOUDS_D("few clouds", "02d", R.drawable.ic_partly_cloudy, R.drawable.h_cloudy_day, R.drawable.day_partlycloudy),
    SCATTERED_CLOUDS_D("scattered clouds", "03d", R.drawable.ic_cloudy, R.drawable.h_cloudy_day, R.drawable.day_cloudy),
    BROKEN_CLOUDS_D("broken clouds", "04d", R.drawable.ic_cloudy, R.drawable.h_cloudy_day, R.drawable.day_cloudy),
    SHOWER_RAIN_D("shower rain", "09d", R.drawable.ic_heavy_rain, R.drawable.h_heavy_rain, R.drawable.day_rain),
    RAIN_D("rain", "10d", R.drawable.ic_rain, R.drawable.h_heavy_rain, R.drawable.day_rain),
    THUNDERSTORM_D("thunderstorm", "11d", R.drawable.ic_thunderstorm, R.drawable.h_heavy_rain, R.drawable.day_rain),
    SNOW_D("snow", "13d", R.drawable.ic_snow, R.drawable.h_snow_night, R.drawable.day_snow),
    MIST_D("mist", "50d", R.drawable.ic_fog, R.drawable.h_snow_day, R.drawable.day_fog),

    CLEAR_SKY_N("clear sky", "01n", R.drawable.ic_clear_night, R.drawable.h_sunny_day, R.drawable.night_clearsky),
    FEW_CLOUDS_N("few clouds", "02n", R.drawable.ic_cloudy_night, R.drawable.h_cloudy_day, R.drawable.night_partlycloudy),
    SCATTERED_CLOUDS_N("scattered clouds", "03n", R.drawable.ic_cloudy, R.drawable.h_cloudy_day, R.drawable.night_cloudy),
    BROKEN_CLOUDS_N("broken clouds", "04n", R.drawable.ic_cloudy, R.drawable.h_cloudy_day, R.drawable.night_cloudy),
    SHOWER_RAIN_N("shower rain", "09n", R.drawable.ic_heavy_rain, R.drawable.h_heavy_rain, R.drawable.night_rain),
    RAIN_N("rain", "10n", R.drawable.ic_rain, R.drawable.h_heavy_rain, R.drawable.night_rain),
    THUNDERSTORM_N("thunderstorm", "11n", R.drawable.ic_thunderstorm, R.drawable.h_heavy_rain, R.drawable.night_rain),
    SNOW_N("snow", "13n", R.drawable.ic_snow, R.drawable.h_snow_night, R.drawable.night_snow),
    MIST_N("mist", "50n", R.drawable.ic_fog, R.drawable.h_snow_day, R.drawable.night_cloudy);

    private String state;
    private String code;
    private int stateURL;
    private int dailyBackgroundURL;
    private int mainBackGroundURL;

    WeatherCode(String state, String code, int stateURL, int dailyBackgroundURL, int mainBackGroundURL) {
        this.state = state;
        this.code = code;
        this.stateURL = stateURL;
        this.dailyBackgroundURL = dailyBackgroundURL;
        this.mainBackGroundURL = mainBackGroundURL;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStateURL() {
        return stateURL;
    }

    public void setStateURL(int stateURL) {
        this.stateURL = stateURL;
    }

    public int getDailyBackgroundURL() {
        return dailyBackgroundURL;
    }

    public void setDailyBackgroundURL(int dailyBackgroundURL) {
        this.dailyBackgroundURL = dailyBackgroundURL;
    }

    public int getMainBackGroundURL() {
        return mainBackGroundURL;
    }

    public void setMainBackGroundURL(int mainBackGroundURL) {
        this.mainBackGroundURL = mainBackGroundURL;
    }
}
