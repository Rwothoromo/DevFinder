package com.example.rwothoromo.developers.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Network Connectivity Utility class.
 */
public class NetworkConnectivity {

    /**
     * Get the network information.
     *
     * @param context the context
     * @return network information
     */
    public static NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo();
    }

    /**
     * Check for network connectivity.
     *
     * @param context the context
     * @return boolean
     */
    public static boolean isConnected(Context context) {
        NetworkInfo info = NetworkConnectivity.getNetworkInfo(context);
        return info != null && info.isConnectedOrConnecting();
    }


}
