package com.wind.the.snowweather.activity;

import android.content.Intent;
import android.os.Bundle;

import com.wind.the.snowweather.R;

public class SWSplashActivity extends SWBaseActivity {
    private Thread mThread;
    /* ---------------------- OVERRIDE ----------------------- */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (hasAllPermissions()) {
            navigateToNextActivity();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (hasAllPermissions()) {
            this.navigateToNextActivity();
        }
    }

    /* ---------------------- EVENT -------------------------- */

    /* ---------------------- GET-SET ------------------------ */

    /* ---------------------- METHOD ------------------------- */

    /**
     * Navigate to next activity
     */
    public void navigateToNextActivity() {
        navigateToActivity(SWMainActivity.class);
    }

    /**
     * Navigate to given activity
     *
     * @param clazz activity class
     */
    private void navigateToActivity(final Class<?> clazz) {
        mThread = new Thread() {
            @Override
            public void run() {
                super.run();
                int waited = 0;
                while (waited < 3500) {
                    try {
                        sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    waited += 100;
                }

                SWSplashActivity.this.finish();
                Intent i = new Intent(SWSplashActivity.this, clazz);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
            }
        };

        mThread.start();

    }

    /* ---------------------- INNER CLASS -------------------- */
}
