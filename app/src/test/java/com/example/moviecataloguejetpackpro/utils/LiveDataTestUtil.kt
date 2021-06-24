package com.example.moviecataloguejetpackpro.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.nio.channels.InterruptedByTimeoutException
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

object LiveDataTestUtil {
    fun <T> getValue(liveData: LiveData<T>): T {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)

        val observer = object : Observer<T> {
            override fun onChanged(t: T) {
                data[0] = t
                latch.countDown()
                liveData.removeObserver(this)
            }
        }

        liveData.observeForever(observer)

        try {
            latch.await(2, TimeUnit.SECONDS)
        } catch (e: InterruptedByTimeoutException) {
            e.printStackTrace()
        }

        return data[0] as T
    }
}