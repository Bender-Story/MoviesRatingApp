package com.android.rahul.movies.utils

import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.android.rahul.movies.BaseTest
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito


class NetworkUtilsTest: BaseTest(){
@Test
fun `Network is Available`(){
    Assert.assertTrue(NetworkUtils.isNetworkAvailable(context))
}

    @Test
    fun `Network is Available by mocking the data`(){
        val connectivityManager= Mockito.mock(ConnectivityManager::class.java)
        val networkInfo= Mockito.mock(NetworkInfo::class.java)
        Mockito.`when`(connectivityManager?.activeNetworkInfo).thenReturn(networkInfo)
        Mockito.`when`(networkInfo?.isConnected).thenReturn(true)
        Assert.assertTrue(NetworkUtils.isNetworkAvailable(context))
    }

}