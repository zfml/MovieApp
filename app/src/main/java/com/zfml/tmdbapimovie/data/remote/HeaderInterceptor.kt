package com.zfml.tmdbapimovie.data.remote

import com.zfml.tmdbapimovie.BuildConfig
import com.zfml.tmdbapimovie.util.Constants.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request  = chain.request().newBuilder()
            .addHeader("Authorization","Bearer ${BuildConfig.API_KEY}")
            .build()

        return chain.proceed(request)
    }
}