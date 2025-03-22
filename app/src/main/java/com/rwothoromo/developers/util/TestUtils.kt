package com.rwothoromo.developers.util

import android.os.Handler
import android.os.Looper
import com.rwothoromo.developers.constants.Constants.DIALOG_DELAY_TIME

object TestUtils {
    fun executeWithDelay(delayMillis: Long = DIALOG_DELAY_TIME, action: () -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed({
            action()
            println("Task executed after $delayMillis delay")
        }, delayMillis)
    }
}
