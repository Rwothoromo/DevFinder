package com.rwothoromo.developers.api

import com.rwothoromo.developers.constants.Constants.DUMMY_GITLAB_API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class AuthTokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalReq = chain.request()
        val reqBuilder = originalReq.newBuilder().header("X-Api-Key", DUMMY_GITLAB_API_KEY)
        val request = reqBuilder.build()

        return chain.proceed(request)
    }
}