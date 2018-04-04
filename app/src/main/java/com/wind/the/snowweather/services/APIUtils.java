package com.wind.the.snowweather.services;

/**
 * Created by DzungVt on 2/11/2018.
 */

public class APIUtils {
    public static final String BASE_URL = "http://api.openweathermap.org/";

    public static APIWeather getApiWeather() {
        return RetrofitClient.getRetrofit(BASE_URL).create(APIWeather.class);
    }
}
