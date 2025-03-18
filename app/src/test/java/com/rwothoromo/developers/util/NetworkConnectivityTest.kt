package com.rwothoromo.developers.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.rwothoromo.developers.util.NetworkConnectivity.isNetworkConnected
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit


/**
 * Network Connectivity test class.
 */
class NetworkConnectivityTest {

    /**
     * NetworkConnectivity Test rule.
     */
    @Rule
    @JvmField
    var mockitoRule = MockitoJUnit.rule()!!
    @Mock
    var networkInfo: NetworkInfo? = null
    @Mock
    internal var connectivityManager: ConnectivityManager? = null
    @Mock
    private val context: Context? = null

    /**
     * Set up the ConnectivityManager and NetworkInfo.
     */
    @Before
    fun setUp() {
        // Return ConnectivityManager.
        `when`(context!!.getSystemService(Context.CONNECTIVITY_SERVICE))
                .thenReturn(connectivityManager)

        // Return NetworkInfo.
        `when`(connectivityManager!!.activeNetworkInfo)
                .thenReturn(networkInfo)
    }

    /**
     * Test that NetworkConnectivity is true when connected to a network.
     */
    @Test
    fun networkConnectivityIsTrueWhenConnectedToNetwork() {
        // Set connection to true.
        `when`(networkInfo!!.isConnectedOrConnecting).thenReturn(true)

        Assert.assertTrue(isNetworkConnected(this.context!!))
    }

    /**
     * Test that NetworkConnectivity is false when not connected to a network.
     */
    @Test
    fun networkConnectivityIsFalseWhenConnectedToNetwork() {
        // Set connection to false.
        `when`(networkInfo!!.isConnectedOrConnecting).thenReturn(false)

        Assert.assertFalse(isNetworkConnected(this.context!!))
    }

}
