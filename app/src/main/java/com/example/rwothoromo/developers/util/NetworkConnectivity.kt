package com.example.rwothoromo.developers.util

import android.content.Context
import android.net.ConnectivityManager

/**
 * Network Connectivity Utility class.
 */
object NetworkConnectivity {

    /**
     * Check for network connectivity.
     *
     * @param context the context
     * @return boolean
     */
    fun isNetworkConnected(context: Context): Boolean {
        val systemService = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return systemService.activeNetworkInfo?.isConnectedOrConnecting ?: false
    }

}
