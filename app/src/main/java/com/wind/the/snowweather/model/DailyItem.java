package com.wind.the.snowweather.model;

/**
 * Created by DzungVt on 2/3/2018.
 */

public class DailyItem {
    String date, status, minTemp, maxTemp, mornTemp, aftTemp, evenTemp, dayTemp;
    int intDailyBackgroundURL, intStateURL;

    public DailyItem(String date, String status, String minTemp, String maxTemp, String mornTemp, String aftTemp, String evenTemp, String dayTemp, int intDailyBackgroundURL, int intStateURL) {
        this.date = date;
        this.status = status;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.mornTemp = mornTemp;
        this.aftTemp = aftTemp;
        this.evenTemp = evenTemp;
        this.dayTemp = dayTemp;
        this.intDailyBackgroundURL = intDailyBackgroundURL;
        this.intStateURL = intStateURL;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getMornTemp() {
        return mornTemp;
    }

    public void setMornTemp(String mornTemp) {
        this.mornTemp = mornTemp;
    }

    public String getAftTemp() {
        return aftTemp;
    }

    public void setAftTemp(String aftTemp) {
        this.aftTemp = aftTemp;
    }

    public String getEvenTemp() {
        return evenTemp;
    }

    public void setEvenTemp(String evenTemp) {
        this.evenTemp = evenTemp;
    }

    public String getDayTemp() {
        return dayTemp;
    }

    public void setDayTemp(String dayTemp) {
        this.dayTemp = dayTemp;
    }

    public int getIntDailyBackgroundURL() {
        return intDailyBackgroundURL;
    }

    public void setIntDailyBackgroundURL(int intDailyBackgroundURL) {
        this.intDailyBackgroundURL = intDailyBackgroundURL;
    }

    public int getIntStateURL() {
        return intStateURL;
    }

    public void setIntStateURL(int intStateURL) {
        this.intStateURL = intStateURL;
    }
}
