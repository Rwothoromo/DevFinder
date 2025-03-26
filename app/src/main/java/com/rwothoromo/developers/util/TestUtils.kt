package com.rwothoromo.developers.util

import android.os.Handler
import android.os.Looper
import com.rwothoromo.developers.constants.Constants.DIALOG_DELAY_TIME

object TestUtils {

    fun executeWithDelay(delayMillis: Long = DIALOG_DELAY_TIME, action: () -> Unit) {

        // Get the Looper associated with the current thread
        val currentLooper = Looper.myLooper()
        if (currentLooper != null) {
            val handler = Handler(currentLooper)
            handler.postDelayed(object : Runnable {
                override fun run() {
                    action()
                    println("Task executed after $delayMillis delay")
                }
            }, delayMillis)
        } else {
            Handler(Looper.getMainLooper()).postDelayed({
                action()
                println("Task executed after $delayMillis delay")
            }, delayMillis)
        }

    }

}
