package com.wind.the.snowweather.services;

import com.wind.the.snowweather.model.weather.CurrentWeather;
import com.wind.the.snowweather.model.weather.DailyWeather;
import com.wind.the.snowweather.model.weather.HoursWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by DzungVt on 2/10/2018.
 */

public interface APIWeather {
    String API_KEY = "e9175124c477e3f404adeca248bdb9e8";

    @GET("/data/2.5/weather?&appid=" + API_KEY)
    Call<CurrentWeather> getCurrentWeather(@Query("q") String city, @Query("units") String units);

    @GET("/data/2.5/forecast/daily?&appid=" + API_KEY)
    Call<DailyWeather> getDailyWeather(@Query("q") String city, @Query("units") String units, @Query("cnt") String days);

    @GET("/data/2.5/forecast?&appid=" + API_KEY)
    Call<HoursWeather> getHoursWeather(@Query("q") String city, @Query("units") String units, @Query("cnt") String days);
}
