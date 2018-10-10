package com.sreeyainfotech.sampleasynchronous.network;

import android.content.Context;
import android.net.ConnectivityManager;

public class WebServices {
    private static final String DOMAIN_NAME = "http://iphone.us2guntur.com/";

    public static final String LOGIN_URL = DOMAIN_NAME+ "AndroidAppLoginService";

    public static final String OFFERS_SERVICE =DOMAIN_NAME+ "OffersPage";

    private static final String TAG = "WebServices";

    /**
     * To check internet connection
     *
     * @param context
     * @return
     * @throws Exception
     */
    public static boolean isNetworkAvailable(Context context) throws Exception {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isFailover())
            return false;
        else if (cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isAvailable()
                && cm.getActiveNetworkInfo().isConnected())
            return true;
        else
            return false;
    }

}
