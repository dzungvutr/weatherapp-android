package com.wind.the.snowweather.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.wind.the.snowweather.R;
import com.wind.the.snowweather.activity.helper.SWCode;
import com.wind.the.snowweather.session.SWSession;

import java.util.HashMap;
import java.util.Map;

/**
 * Override life cycle event.
 * <ul>Common life cycle
 * <li>Start new activity: onCreate -> OnStart -> OnResume</li>
 * <li>Hilde activity to background or another activity come into foreground:
 * onSaveInstanceState -> OnPause -> OnStop</li>
 * <li>Restart activity from background to foreground: OnRestart -> OnStart -> OnResume</li>
 * <li>Destroy activity: OnPause -> OnStop -> OnDestroy</li>
 * </ul>
 */
public class SWBaseActivity extends AppCompatActivity {

    // permission
    protected final String PERMISSIONS[] = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    // tagged objects
    private transient Map<String, Object> mTags = new HashMap<>();

    /* ---------------------- OVERRIDE ----------------------- */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*===================SET COLOR STATUS BAR====================*/
        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorStatusBar));
        }


        // init session
        if (hasAllPermissions()) {
            SWSession.init(this);
        }

        // hide default title bar
        // this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // hide keyboard when activity start
        //this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        // request permissions
        this.requestAllPermission();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case SWCode.REQUEST_ALL_PERMISSIONS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    this.requestAllPermission();
                } else {
                    SWSession.init(this);
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    /* ---------------------- EVENT -------------------------- */

    /* ---------------------- GET-SET ------------------------ */

    /**
     * Map of tagged objects
     *
     * @return all tagged object
     */
    public Map<String, Object> getTags() {
        if (mTags == null) {
            mTags = new HashMap<>();
        }
        return mTags;
    }

    /**
     * Get tagged object which is set by setTag(obj) method
     *
     * @return tagged object
     */
    public Object getTag() {
        return getTag(getClass().getName());
    }

    /**
     * Tag an object to this tool
     *
     * @param taggedObj tagged object
     */
    public void setTag(Object taggedObj) {
        addTag(getClass().getName(), taggedObj);
    }

    /**
     * Get tagged object by key
     *
     * @param key tagged key
     * @return tagged object
     */
    public Object getTag(String key) {
        return getTags().get(key);
    }

    /**
     * Add tagged object
     *
     * @param key       tagged key
     * @param taggedObj tagged object
     */
    public void addTag(String key, Object taggedObj) {
        if (mTags == null) {
            mTags = new HashMap<>();
        }
        mTags.put(key, taggedObj);
    }

    /* ---------------------- METHOD ------------------------- */

    /**
     * Check where app has specific permissions or not
     *
     * @param permissions permissions
     * @return true if has permissions
     */
    protected boolean hasPermission(String... permissions) {
        for (String permit : permissions) {
            if (ContextCompat.checkSelfPermission(this, permit) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check whether app has all necessary permissions or not
     *
     * @return true if has all permissions
     */
    protected boolean hasAllPermissions() {
        return hasPermission(PERMISSIONS);
    }

    /**
     * Request all permission
     */
    protected void requestAllPermission() {
        if (!hasPermission(PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, SWCode.REQUEST_ALL_PERMISSIONS);
        }
    }

    /**
     * Request permission for storage accessing
     */
    public void requestStorageAccess() {
        if (!hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, SWCode.REQUEST_STORAGE_ACCESS);
        }
    }


}

