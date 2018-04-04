package com.wind.the.snowweather.model.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by DzungVt on 2/10/2018.
 */

public class Main {

    @SerializedName("temp")
    @Expose
    private Float temp;
    @SerializedName("pressure")
    @Expose
    private Float pressure;
    @SerializedName("humidity")
    @Expose
    private Integer humidity;
    @SerializedName("temp_min")
    @Expose
    private Float tmpMin;
    @SerializedName("temp_max")
    @Expose
    private Float tmpMax;

    public Float getTemp() {
        return temp;
    }

    public void setTemp(Float temp) {
        this.temp = temp;
    }

    public Float getPressure() {
        return pressure;
    }

    public void setPressure(Float pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
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
}
