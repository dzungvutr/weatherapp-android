package com.wind.the.snowweather.session;

import android.content.Context;

public final class SWSession {

    private static SWStoreHelper mStoreHelper;

    /* ---------------------- STATIC ------------------------- */

    /* ---------------------- OVERRIDE ----------------------- */

    /* ---------------------- EVENT -------------------------- */

    /* ---------------------- GET-SET ------------------------ */

    /**
     * Get store helper
     *
     * @return store helper
     */
    public static SWStoreHelper getStoreHelper() {
        return mStoreHelper;
    }

    /* ---------------------- METHOD ------------------------- */

    /**
     * Init session
     *
     * @param context application context
     */
    public static void init(Context context) {
        // init store helper
        mStoreHelper = new SWStoreHelper(context);
        mStoreHelper.initApplicationDirs();

        // TODO: init database helper

    }

    /* ---------------------- INNER CLASS -------------------- */

}
