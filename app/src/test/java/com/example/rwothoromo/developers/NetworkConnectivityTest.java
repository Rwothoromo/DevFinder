package com.example.rwothoromo.developers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.rwothoromo.developers.util.NetworkConnectivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Network Connectivity test class.
 */
public class NetworkConnectivityTest {

    /**
     * NetworkConnectivity Test rule.
     */
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Mock
    NetworkInfo networkInfo;
    @Mock
    ConnectivityManager connectivityManager;
    @Mock
    private Context context;

    /**
     * Set up the ConnectivityManager and NetworkInfo.
     */
    @Before
    public void setUp() {
        // Return ConnectivityManager.
        when(context.getSystemService(Context.CONNECTIVITY_SERVICE))
                .thenReturn(connectivityManager);

        // Return NetworkInfo.
        when(connectivityManager.getActiveNetworkInfo())
                .thenReturn(networkInfo);
    }

    /**
     * Test that NetworkConnectivity is true when connected to a network.
     */
    @Test
    public void networkConnectivityIsTrueWhenConnectedToNetwork() {
        // Set connection to true.
        when(networkInfo.isConnectedOrConnecting()).thenReturn(true);

        Assert.assertTrue(NetworkConnectivity.isConnected(context));

        verify(networkInfo).isConnectedOrConnecting();
    }

    /**
     * Test that NetworkConnectivity is false when not connected to a network.
     */
    @Test
    public void networkConnectivityIsFalseWhenConnectedToNetwork() {
        // Set connection to false.
        when(networkInfo.isConnectedOrConnecting()).thenReturn(false);

        Assert.assertFalse(NetworkConnectivity.isConnected(context));
    }
}
