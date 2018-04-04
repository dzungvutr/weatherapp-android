package com.wind.the.snowweather.activity.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.JsonIOException;
import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.EdgeDetail;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.wind.the.snowweather.AppConfig;
import com.wind.the.snowweather.R;
import com.wind.the.snowweather.activity.adapter.HoursViewAdapter;
import com.wind.the.snowweather.model.HoursItem;
import com.wind.the.snowweather.model.weather.CurrentWeather;
import com.wind.the.snowweather.model.weather.DailyWeather;
import com.wind.the.snowweather.model.weather.HoursWeather;
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

public class SWTodayFragment extends Fragment {
    LinearLayout lnlNameLocation, lnlFrgToday;
    TextView txtCountry, txtCity, txtCurrentTemp, txtMinTemp, txtMaxTemp, txtStatus, txtContent;
    TextView txtHumidity, txtClouds, txtPressure, txtWind, txtDay, txtDate, txtVisibility;
    TextView txtPercentHumidity, txtPercentClouds, txtSunset, txtSunrise, txtLongitude, txtLatitude;
    EditText edtCity;
    ImageView imgLocation, imgCancel, imgSearch, imgStatus, imgPressure, imgWind, imgSunrise, imgSunset, imgCoordinates;
    String cityName;
    DecoView humidityView, cloudsView;
    RecyclerView rcvHours;
    LinearLayout lnlMain;
    List<HoursItem> mHoursItem;
    HoursViewAdapter hoursViewAdapter;
    APIWeather apiWeather;
    boolean checkView = false;
    DataFragmentManager dataFragmentManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DataFragmentManager) {
            dataFragmentManager = (DataFragmentManager) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement onViewSelected");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_today, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapping();


        mHoursItem = new LinkedList<>();
        cityName = SWSession.getStoreHelper().getSharedPrefs().getString(AppConfig.PREFS_CITY_NAME, "");
        if (cityName.equals("")) {
            cityName = "Hanoi";
//            SharedPreferences.Editor editor = SWSession.getStoreHelper().getSharedPrefs().edit();
//            editor.putString(AppConfig.PREFS_CITY_NAME, "Hanoi");
//            editor.commit();
        }
        try {
            GetCurrentWeatherData(cityName, AppConfig.UNITS_METRIC, "10");
        } catch (Exception e) {
            Log.i("GET LOG - ", e.toString());
            e.printStackTrace();
        }


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rcvHours.setLayoutManager(layoutManager);
        hoursViewAdapter = new HoursViewAdapter(getContext(), mHoursItem);
        rcvHours.setAdapter(hoursViewAdapter);


        /*=================================HANDLER CLICK===========================================*/
        imgLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSearch();
            }
        });

        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideSearch(view);
            }
        });

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tmpCityName = edtCity.getText().toString();

                if (tmpCityName.equals("")) {
                    hideSearch(view);
                } else {
                    cityName = tmpCityName;
                    //dataFragmentManager.onDataSelected(cityName);

                    GetCurrentWeatherData(cityName, AppConfig.UNITS_METRIC, "1");
                    hideSearch(view);
                }


            }
        });


    }

    /*=============================METHOD=======================================*/

    private void GetCurrentWeatherData(String city, String units, String days) {
        apiWeather = APIUtils.getApiWeather();
        Log.d("CHECK GETWEATHER - ", "OK");
        /*======================GET CURRENT============================*/
        apiWeather.getCurrentWeather(city, units).enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                try {
                    if (response.isSuccessful()) {
                        lnlMain.setBackgroundResource(SWStringUtils.convertBackGroundMainInt(response.body().getWeather().get(0).getIcon()));
                        txtCity.setText(response.body().getName());
                        txtCountry.setText(SWStringUtils.convertA2CodeToCountry(response.body().getSys().getCountry()));
                        txtCurrentTemp.setText(String.valueOf(Math.round(response.body().getMain().getTemp())) + "°C");
                        //        txtMinTemp.setText(String.valueOf(Math.round(response.body().getMain().getTmpMin())) + "°C");
                        //        txtMaxTemp.setText(String.valueOf(Math.round(response.body().getMain().getTmpMax())) + "°C");
                        txtStatus.setText(response.body().getWeather().get(0).getMain());
                        txtContent.setText(response.body().getWeather().get(0).getDescription());
//                        String iconId = response.body().getWeather().get(0).getIcon();
//                        Picasso.with(SWTodayFragment.super.getContext()).load("http://openweathermap.org/img/w/" + iconId + ".png").into(imgStatus);

                        imgStatus.setImageResource(SWStringUtils.convertStateInt(response.body().getWeather().get(0).getIcon()));
                        txtWind.setText(String.valueOf(response.body().getWind().getSpeed()) + " m/s");
                        //txtHumidity.setText(String.valueOf(response.body().getMain().getHumidity()) + " %");
                        txtSunrise.setText(SWDateConvert.convertHours(response.body().getSys().getSunrise()));
                        txtSunset.setText(SWDateConvert.convertHours(response.body().getSys().getSunset()));
                        txtLongitude.setText(String.valueOf(response.body().getCoord().getLon()) + "° E");
                        txtLatitude.setText(String.valueOf(response.body().getCoord().getLat()) + "° N");
                        txtVisibility.setText(SWStringUtils.convertMetreToKilometer(response.body().getVisibility()) + " km");

                        txtPercentHumidity.setText(String.format("%.0f%%", Float.valueOf(response.body().getMain().getHumidity())));
                        addDecoView(humidityView, Float.valueOf(response.body().getMain().getHumidity()));
                        txtPressure.setText(String.valueOf(Math.round(response.body().getMain().getPressure())) + " hPa");
                        //txtClouds.setText(String.valueOf(response.body().getClouds().getAll()));
                        txtPercentClouds.setText(String.format("%.0f%%", Float.valueOf(response.body().getClouds().getAll())));
                        addDecoView(cloudsView, Float.valueOf(response.body().getClouds().getAll()));
                        imgPressure.setImageResource(R.drawable.ic_pressure_01);
                        imgWind.setImageResource(R.drawable.ic_wind_turbine);
                        imgSunrise.setImageResource(R.drawable.ic_sunrise_02);
                        imgSunset.setImageResource(R.drawable.ic_sunset_01);
                        imgCoordinates.setImageResource(R.drawable.ic_compass);
                        imgLocation.setImageResource(R.drawable.ic_location_searching_red_900_24dp);
                        //Date time
                        txtDay.setText(SWDateConvert.convertDay(response.body().getDt()));
                        txtDate.setText(SWDateConvert.convertDate(response.body().getDt()));

                        SharedPreferences.Editor editor = SWSession.getStoreHelper().getSharedPrefs().edit();
                        editor.putString(AppConfig.PREFS_CITY_NAME, cityName);
                        editor.commit();

                    } else {
                        int statusCode = response.code();
                        Log.d("RESPONSE ERROR - ", String.valueOf(statusCode));
                    }
                } catch (JsonIOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {
                t.printStackTrace();
            }
        });

        /*=====================GET DAILY=====================*/
        apiWeather.getDailyWeather(city, units, days).enqueue(new Callback<DailyWeather>() {
            @Override
            public void onResponse(Call<DailyWeather> call, Response<DailyWeather> response) {
                try {
                    if (response.isSuccessful()) {
                        txtMinTemp.setText("↓ " + String.valueOf(Math.round(response.body().getListDaily().get(0).getTemp().getTmpMin())) + "°C");
                        txtMaxTemp.setText("↑ " + String.valueOf(Math.round(response.body().getListDaily().get(0).getTemp().getTmpMax())) + "°C");
                    } else {
                        int statusCode = response.code();
                        Log.d("RESPONSE ERROR - ", String.valueOf(statusCode));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<DailyWeather> call, Throwable t) {
                t.printStackTrace();
            }
        });

        /*===========================GET HOURS===========================*/
        apiWeather.getHoursWeather(city, units, "20").enqueue(new Callback<HoursWeather>() {
            @Override
            public void onResponse(Call<HoursWeather> call, Response<HoursWeather> response) {
                try {
                    if (response.isSuccessful()) {
                        if (mHoursItem != null) {
                            if (!mHoursItem.isEmpty()) {
                                mHoursItem.clear();
                            }
                        }
                        for (int i = 0; i < response.body().getCnt(); i++) {
                            mHoursItem.add(new HoursItem(SWStringUtils.subHours(response.body().getListHours().get(i).getDtTxt())
                                    , String.valueOf(Math.round(response.body().getListHours().get(i).getMainHours().getTemp())) + "°C"
                                    , SWStringUtils.convertStateInt(response.body().getListHours().get(i).getWeather().get(0).getIcon())
                                    , SWStringUtils.convertBackgroundHoursInt(SWStringUtils.subHours(response.body().getListHours().get(i).getDtTxt()))
                            ));
                        }
                    } else {
                        int statusCode = response.code();
                        Log.d("RESPONSE ERROR - ", String.valueOf(statusCode));
                    }
                    hoursViewAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<HoursWeather> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void addDecoView(DecoView decoView, float values) {
        decoView.addSeries(new SeriesItem.Builder(Color.parseColor("#c4cec7"))
                .setRange(0, 100, 100)
                .setInitialVisibility(true)
                .setLineWidth(26f)
                .build());

        //Create data series track
        SeriesItem seriesItem = new SeriesItem.Builder(Color.parseColor("#FF219EE7"))
                .setRange(0, 100, values)
                .addEdgeDetail(new EdgeDetail(EdgeDetail.EdgeType.EDGE_OUTER, Color.parseColor("#22000000"), 0.4f))
                //.setSeriesLabel(new SeriesLabel.Builder("Percent %.0f%%").build())
//                .setColorBack(Color.argb(218, 0, 0, 0))
//                .setColorText(Color.argb(255, 255, 255, 255))
                .setLineWidth(26f)
                .build();
        /*"#db611a"*/

        decoView.addSeries(seriesItem);

//        seriesItem.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
//            String format = "%.0f%%";
//
//            @Override
//            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
//                if (format.contains("%%")) {
//                    float percentFilled = ((currentPosition - seriesItem.getMinValue()) / (seriesItem.getMaxValue() - seriesItem.getMinValue()));
//                    view.setText(String.format(format, percentFilled * 100f));
//                } else {
//                    view.setText(String.format(format, currentPosition));
//                }
//
//                @Override
//                public void onSeriesItemDisplayProgress ( float percentComplete){
//
//                }
//            });

//        decoView.addEvent(new DecoEvent.Builder(DecoEvent.EventType.EVENT_SHOW, true)
//                .setDelay(1000)
//                .setDuration(2000)
//                .build());
//
//        decoView.addEvent(new DecoEvent.Builder(25).setIndex(series1Index).setDelay(4000).build());
//        decoView.addEvent(new DecoEvent.Builder(100).setIndex(series1Index).setDelay(8000).build());
//        decoView.addEvent(new DecoEvent.Builder(10).setIndex(series1Index).setDelay(12000).build());
    }

    private void mapping() {
        cloudsView = getView().findViewById(R.id.pcClouds);
        humidityView = getView().findViewById(R.id.pcHumidity);
        lnlNameLocation = getView().findViewById(R.id.lnlNameLocation);
        txtCountry = getView().findViewById(R.id.txtCountry);
        txtCity = getView().findViewById(R.id.txtCity);
        txtCurrentTemp = getView().findViewById(R.id.txtCurrentTemp);
        txtMinTemp = getView().findViewById(R.id.txtMinTemp);
        txtMaxTemp = getView().findViewById(R.id.txtMaxTemp);
        txtStatus = getView().findViewById(R.id.txtStatus);
        txtContent = getView().findViewById(R.id.txtContent);
        txtClouds = getView().findViewById(R.id.txtClouds);
        txtPressure = getView().findViewById(R.id.txtPressure);
        txtWind = getView().findViewById(R.id.txtWind);
        txtHumidity = getView().findViewById(R.id.txtHumidity);
        txtDay = getView().findViewById(R.id.txtDay);
        txtDate = getView().findViewById(R.id.txtDate);
        txtPercentHumidity = getView().findViewById(R.id.txtPercentHumidity);
        txtPercentClouds = getView().findViewById(R.id.txtPercentClouds);
        txtSunset = getView().findViewById(R.id.txtSunset);
        txtSunrise = getView().findViewById(R.id.txtSunrise);
        txtLongitude = getView().findViewById(R.id.txtLongitude);
        txtLatitude = getView().findViewById(R.id.txtLatitude);
        txtVisibility = getView().findViewById(R.id.txtVisibility);
        edtCity = getView().findViewById(R.id.edtCity);
        imgCancel = getView().findViewById(R.id.imgCancel);
        imgLocation = getView().findViewById(R.id.imgLocation);
        imgSearch = getView().findViewById(R.id.imgSearch);
        imgStatus = getView().findViewById(R.id.imgStatus);
        imgPressure = getView().findViewById(R.id.imgPressure);
        imgWind = getView().findViewById(R.id.imgWind);
        imgSunrise = getView().findViewById(R.id.imgSunrise);
        imgSunset = getView().findViewById(R.id.imgSunset);
        imgCoordinates = getView().findViewById(R.id.imgCoordinates);
        rcvHours = getView().findViewById(R.id.rcvHours);
        lnlFrgToday = getView().findViewById(R.id.lnlFrgToday);
        lnlMain = getActivity().findViewById(R.id.lnlMain);
    }

    private void showSearch() {
        if (checkView == false) {
            lnlNameLocation.setVisibility(View.GONE);
            imgLocation.setVisibility(View.GONE);
            imgCancel.setVisibility(View.VISIBLE);
            edtCity.setVisibility(View.VISIBLE);
            imgSearch.setVisibility(View.VISIBLE);
            checkView = true;
        } else {
            checkView = false;
        }
    }

    private void hideSearch(View view) {

        if (checkView == true) {
            lnlNameLocation.setVisibility(View.VISIBLE);
            imgLocation.setVisibility(View.VISIBLE);
            imgCancel.setVisibility(View.GONE);
            edtCity.setVisibility(View.GONE);
            imgSearch.setVisibility(View.GONE);
            checkView = false;
        } else {
            checkView = true;
        }

        //Hide soft keyboard
        if (view.getWindowToken() != null) {
            try {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
