/*
 * Copyright 2015, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rwothoromo.developers.util

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource
import com.rwothoromo.developers.constants.Constants.RESOURCE

/**
 * Contains a static reference to IdlingResource, only available in the 'mock' build type.
 */
object EspressoIdlingResource {

    @JvmField
    val mCountingIdlingResource = CountingIdlingResource(RESOURCE)

    /**
     * Returns instance of IdlingResource.
     *
     * @return resource instance
     */
    val countingIdlingResource: IdlingResource
        get() = mCountingIdlingResource

    /**
     * Increment counter.
     */
    fun increment() {
        mCountingIdlingResource.increment()
    }

    /**
     * Decrement counter.
     */
    fun decrement() {
        if (!mCountingIdlingResource.isIdleNow) {
            mCountingIdlingResource.decrement()
        }
    }
}
