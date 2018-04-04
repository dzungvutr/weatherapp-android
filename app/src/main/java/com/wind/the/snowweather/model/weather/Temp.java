package com.wind.the.snowweather.model.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by DzungVt on 2/10/2018.
 */

public class Temp {
    @SerializedName("day")
    @Expose
    private Float tmpDay;
    @SerializedName("min")
    @Expose
    private Float tmpMin;
    @SerializedName("max")
    @Expose
    private Float tmpMax;
    @SerializedName("night")
    @Expose
    private Float tmpNight;
    @SerializedName("eve")
    @Expose
    private Float tmpEve;
    @SerializedName("morn")
    @Expose
    private Float tmpMorn;

    public Float getTmpDay() {
        return tmpDay;
    }

    public void setTmpDay(Float tmpDay) {
        this.tmpDay = tmpDay;
    }

    public Float getTmpMin() {
        return tmpMin;
    }

    public void setTmpMin(Float tmpMin) {
        this.tmpMin = tmpMin;
    }

    public Float getTmpMax() {
        return tmpMax;
    }

    public void setTmpMax(Float tmpMax) {
        this.tmpMax = tmpMax;
    }

    public Float getTmpNight() {
        return tmpNight;
    }

    public void setTmpNight(Float tmpNight) {
        this.tmpNight = tmpNight;
    }

    public Float getTmpEve() {
        return tmpEve;
    }

    public void setTmpEve(Float tmpEve) {
        this.tmpEve = tmpEve;
    }

    public Float getTmpMorn() {
        return tmpMorn;
    }

    public void setTmpMorn(Float tmpMorn) {
        this.tmpMorn = tmpMorn;
    }
}
