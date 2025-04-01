package com.rwothoromo.developers.util

import android.os.Handler
import android.os.Looper
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.IdlingResource.ResourceCallback

class DelayIdlingResource(private val delayMillis: Long) : IdlingResource {
    private var callback: ResourceCallback? = null
    private val handler = Handler(Looper.getMainLooper())

    override fun getName(): String = "DelayIdlingResource"

    override fun isIdleNow(): Boolean {
        callback?.onTransitionToIdle()
        return true
    }

    override fun registerIdleTransitionCallback(callback: ResourceCallback?) {
        this.callback = callback
    }

    fun delay(runnable: Runnable) {
        handler.postDelayed(runnable, delayMillis)
        IdlingRegistry.getInstance().register(this)

        handler.postDelayed({
            IdlingRegistry.getInstance().unregister(this)
        }, delayMillis)
    }
}
