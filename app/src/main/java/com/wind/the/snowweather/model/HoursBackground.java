package com.wind.the.snowweather.model;

import com.wind.the.snowweather.R;

/**
 * Created by DzungVt on 2/28/2018.
 */

public enum HoursBackground {
    MORNING(R.drawable.hbg_morning_200px),
    AFTERNOON(R.drawable.hbg_evening_200px),
    EVENING(R.drawable.hbg_night_200px);
    private int URL;

    HoursBackground(int URL) {

        this.URL = URL;
    }

    public int getURL() {
        return URL;
    }
}
