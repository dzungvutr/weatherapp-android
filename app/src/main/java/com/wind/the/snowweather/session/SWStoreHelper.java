package com.wind.the.snowweather.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import com.wind.the.snowweather.AppConfig;
import com.wind.the.snowweather.utils.SWFileUtils;

import java.io.File;

/**
 * Provide function for store data internally, externally for globally
 */
public class SWStoreHelper {

    private SharedPreferences mSharedPrefs;

    /**
     * Constructor
     *
     * @param context activity context
     */
    public SWStoreHelper(Context context) {
        mSharedPrefs = context.getSharedPreferences(AppConfig.SHARE_PREFS_NAME, Context.MODE_PRIVATE);
    }

    /* ---------------------- OVERRIDE ------------------------- */

    /* --------------------- GET-SET ------------------------- */

    /**
     * Get Android shared preferences
     *
     * @return shared preferences
     */
    public SharedPreferences getSharedPrefs() {
        return mSharedPrefs;
    }

    /**
     * Get relative path which is relative to application folder which is set at GMGlobal.APP_FOLDER.</p>
     * Example: <p/>
     * Absolute path: "/storage/emulated/0/{APP_FOLDER}/image/orchid.jpg" </p>
     * Relative path: "image/orchid.jpg"
     *
     * @param path path
     * @return relative path.
     */
    public String getRelativePath(String path) {
        File root = getRootDir();
        String rootPath = root.getAbsolutePath();

        if (path.length() > rootPath.length() && path.startsWith(rootPath)) {
            String relative = path.substring(rootPath.length() + 1);
            return relative.isEmpty() ? path : relative;
        }
        return path;
    }

    /**
     * Get absolute path which derive from root of android storage.</p>
     * Example: <p/>
     * Absolute path: "/storage/emulated/0/{APP_FOLDER}/image/orchid.jpg" </p>
     * Relative path: "image/orchid.jpg"
     *
     * @param path path
     * @return absolute path.
     */
    public String getAbsolutePath(String path) {
        File root = getRootDir();
        if (path.startsWith(root.getAbsolutePath())) {
            return path;
        }
        return SWFileUtils.joint(root, path).getAbsolutePath();
    }

    /**
     * Get root directory
     *
     * @return root directory. "${ANDROID_ROOT}/${APP_FOLDER}"
     */
    public File getRootDir() {
        return SWFileUtils.joint(Environment.getExternalStorageDirectory(), AppConfig.APP_FOLDER);
    }

    /**
     * Get absolute experiments directory
     *
     * @return "${ANDROID_ROOT}/${APP_FOLDER}/images"
     */
    public File getImageDir() {
        return SWFileUtils.joint(getRootDir(), "images");
    }

    /* ---------------------- METHOD --------------------------- */

    /**
     * Initialize application directories
     * <p>
     * <pre>
     * |- APP_FOLDER
     * |    |- images
     *          |- sub
     *      |- json
     * </pre>
     * <p>
     * <ul>
     * <li>GMGlobal.APP_FOLDER: Root application directory</li>
     * </ul>
     */
    public void initApplicationDirs() {
        // create root dir
        getRootDir().mkdirs();

        // create image dir
        getImageDir().mkdirs();
    }
}
