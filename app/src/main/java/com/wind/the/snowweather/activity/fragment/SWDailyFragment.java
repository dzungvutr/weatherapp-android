package com.wind.the.snowweather.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.JsonIOException;
import com.wind.the.snowweather.AppConfig;
import com.wind.the.snowweather.R;
import com.wind.the.snowweather.activity.adapter.DailyViewAdapter;
import com.wind.the.snowweather.model.DailyItem;
import com.wind.the.snowweather.model.weather.DailyWeather;
import com.wind.the.snowweather.services.APIUtils;
import com.wind.the.snowweather.services.APIWeather;
import com.wind.the.snowweather.session.SWSession;
import com.wind.the.snowweather.utils.DataFragmentManager;
import com.wind.the.snowweather.utils.SWDateConvert;
import com.wind.the.snowweather.utils.SWStringUtils;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by DzungVt on 2/2/2018.
 */

public class SWDailyFragment extends Fragment implements DataFragmentManager {
    RecyclerView rcvDaily;
    List<DailyItem> mDailyData;
    DailyViewAdapter dailyViewAdapter;
    String cityName;
    String tmpCityName;
    LinearLayout lnlFrgMDaily;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //cityName = getArguments().getString("cityToDaily");

        return inflater.inflate(R.layout.activity_daily, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lnlFrgMDaily = view.findViewById(R.id.lnlFrgMDaily);
        rcvDaily = view.findViewById(R.id.rcvDaily);
        rcvDaily.setLayoutManager(new LinearLayoutManager(getContext()));
        mDailyData = new LinkedList<>();
        cityName = SWSession.getStoreHelper().getSharedPrefs().getString(AppConfig.PREFS_CITY_NAME, "");
        tmpCityName = cityName;
        if (cityName != null) {
            GetDailyWeatherData(cityName, AppConfig.UNITS_METRIC, "15");
        }
        dailyViewAdapter = new DailyViewAdapter(mDailyData, getContext());
        rcvDaily.setAdapter(dailyViewAdapter);

    }


    private void GetDailyWeatherData(String city, String units, String days) {
        // mDailyData.clear();
        APIWeather apiWeather = APIUtils.getApiWeather();
        apiWeather.getDailyWeather(city, units, days).enqueue(new Callback<DailyWeather>() {
            @Override
            public void onResponse(Call<DailyWeather> call, Response<DailyWeather> response) {
                try {
                    if (response.isSuccessful()) {
                        if (mDailyData != null) {
                            if (!mDailyData.isEmpty()) {
                                mDailyData.clear();
                            }
                        }
                        for (int i = 0; i < response.body().getCnt(); i++) {
                            mDailyData.add(new DailyItem(SWDateConvert.convertDayDate(response.body().getListDaily().get(i).getDt())
                                    , response.body().getListDaily().get(i).getWeather().get(0).getMain()
                                    , String.valueOf("↓ " + Math.round(response.body().getListDaily().get(i).getTemp().getTmpMin()) + "°C")
                                    , String.valueOf("↑ " + Math.round(response.body().getListDaily().get(i).getTemp().getTmpMax()) + "°C")
                                    , String.valueOf(Math.round(response.body().getListDaily().get(i).getTemp().getTmpMorn()) + "°C")
                                    , String.valueOf(Math.round(response.body().getListDaily().get(i).getTemp().getTmpEve()) + "°C")
                                    , String.valueOf(Math.round(response.body().getListDaily().get(i).getTemp().getTmpEve()) + "°C")
                                    , String.valueOf(Math.round(response.body().getListDaily().get(i).getTemp().getTmpDay()) + "°C")
                                    , SWStringUtils.convertBackgroundDailyInt(response.body().getListDaily().get(i).getWeather().get(0).getIcon())
                                    , SWStringUtils.convertStateInt(response.body().getListDaily().get(i).getWeather().get(0).getIcon())
                            ));
                        }

                        dailyViewAdapter.notifyDataSetChanged();

                    } else {
                        int statusCode = response.code();
                        Log.d("RESPONSE ERROR - ", String.valueOf(statusCode));
                    }
                } catch (JsonIOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<DailyWeather> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onDataSelected(String data) {

    }

    @Override
    public void refreshFragment() {
        cityName = SWSession.getStoreHelper().getSharedPrefs().getString(AppConfig.PREFS_CITY_NAME, "");
        if (!cityName.equals(tmpCityName)) {
            if (cityName != null) {
                tmpCityName = cityName;
                GetDailyWeatherData(cityName, AppConfig.UNITS_METRIC, "15");
            }
        }

    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//    }

    @Override
    public void testData(String data) {
        Log.d("CHECK - ", data);
    }
}


