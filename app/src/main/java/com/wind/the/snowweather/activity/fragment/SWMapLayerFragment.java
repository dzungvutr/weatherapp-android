package com.wind.the.snowweather.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wind.the.snowweather.R;

/**
 * Created by DzungVt on 2/2/2018.
 */

public class SWMapLayerFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_map_layer, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        APIWeather apiWeather = APIUtils.getApiWeather();
//        apiWeather.getHoursWeather("hanoi", AppConfig.UNITS_METRIC, "1").enqueue(new Callback<HoursWeather>() {
//            @Override
//            public void onResponse(Call<HoursWeather> call, Response<HoursWeather> response) {
//                try {
//                    if (response.isSuccessful()) {
//                        Log.d("MAPLAYER - ", response.body().getCity().getName());
//                    } else {
//                        int statusCode = response.code();
//                        Log.d("RESPONSE ERROR - ", String.valueOf(statusCode));
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<HoursWeather> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });
    }


}
