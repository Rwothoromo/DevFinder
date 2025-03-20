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
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule


/**
 * Network Connectivity test class.
 */
class NetworkConnectivityTest {

    /**
     * NetworkConnectivity Test rule.
     */
    @JvmField
    @Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private var networkInfo: NetworkInfo = mock(NetworkInfo::class.java)

    @Mock
    private var connectivityManager: ConnectivityManager = mock(ConnectivityManager::class.java)

    @Mock
    private val mockContext: Context = mock(Context::class.java)

    /**
     * Set up the ConnectivityManager and NetworkInfo.
     */
    @Before
    fun setUp() {
        // Return ConnectivityManager.
        `when`(mockContext.getSystemService(Context.CONNECTIVITY_SERVICE))
            .thenReturn(connectivityManager)

        // Return NetworkInfo.
        `when`(connectivityManager.activeNetworkInfo)
            .thenReturn(networkInfo)
    }

    /**
     * Test that NetworkConnectivity is true when connected to a network.
     */
    @Test
    fun networkConnectivityIsTrueWhenConnectedToNetwork() {
        // Set connection to true.
        `when`(networkInfo.isConnectedOrConnecting).thenReturn(true)
        `when`(networkInfo.isConnected).thenReturn(true)

        Assert.assertTrue(isNetworkConnected(mockContext))
    }

    /**
     * Test that NetworkConnectivity is false when not connected to a network.
     */
    @Test
    fun networkConnectivityIsFalseWhenNotConnectedToNetwork() {
        // Set connection to false.
        `when`(networkInfo.isConnectedOrConnecting).thenReturn(false)
        `when`(networkInfo.isConnected).thenReturn(false)
    }

}
