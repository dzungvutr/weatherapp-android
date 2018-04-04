package com.wind.the.snowweather.model;

/**
 * Created by DzungVt on 2/27/2018.
 */

public class HoursItem {
    private String hours, hoursTemp;
    private int intURL, timeOfDay;

    public HoursItem(String hours, String hoursTemp, int intURL, int timeOfDay) {
        this.hours = hours;
        this.hoursTemp = hoursTemp;
        this.intURL = intURL;
        this.timeOfDay = timeOfDay;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getHoursTemp() {
        return hoursTemp;
    }

    public void setHoursTemp(String hoursTemp) {
        this.hoursTemp = hoursTemp;
    }

    public int getIntURL() {
        return intURL;
    }

    public void setIntURL(int intURL) {
        this.intURL = intURL;
    }

    public int getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(int timeOfDay) {
        this.timeOfDay = timeOfDay;
    }
}
