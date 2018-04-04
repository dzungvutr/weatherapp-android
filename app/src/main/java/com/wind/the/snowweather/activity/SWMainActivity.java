package com.wind.the.snowweather.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.wind.the.snowweather.R;
import com.wind.the.snowweather.activity.fragment.SWDailyFragment;
import com.wind.the.snowweather.activity.fragment.SWMapLayerFragment;
import com.wind.the.snowweather.activity.fragment.SWTodayFragment;
import com.wind.the.snowweather.utils.DataFragmentManager;

public class SWMainActivity extends SWBaseActivity implements DataFragmentManager {
    ViewPager viewPager;
    SmartTabLayout smartTab;
    FragmentPagerItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //SWSession.init(this);
        //SmartTab
        adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(R.string.tabToday, SWTodayFragment.class)
                .add(R.string.tabDaily, SWDailyFragment.class)
                .add(R.string.tabMapLayer, SWMapLayerFragment.class)
                .create()
        );

        viewPager = findViewById(R.id.viewPaper);
        smartTab = findViewById(R.id.smartTab);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(adapter);

        smartTab.setViewPager(viewPager);
        smartTab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Fragment fragment = (Fragment) adapter.instantiateItem(viewPager, position);
                if (fragment != null) {
                    if (fragment.getClass().equals(SWDailyFragment.class)) {
                        ((SWDailyFragment) fragment).refreshFragment();
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    @Override
    public void onDataSelected(String data) {
        Log.d("CITYNAME - ", data);
        Bundle bundle = new Bundle();
        bundle.putString("cityToDaily", data);
        SWDailyFragment swDaily = new SWDailyFragment();
        swDaily.setArguments(bundle);
    }

    @Override
    public void refreshFragment() {

    }

    @Override
    public void testData(String data) {

    }
}
