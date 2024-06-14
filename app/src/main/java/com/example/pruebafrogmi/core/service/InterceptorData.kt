package com.example.pruebafrogmi.core.service

import android.content.Context
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

object InterceptorData {

    fun getInterceptorData(
        context: Context,
        authorization: String,
        XCompanyUuid: String
    ): MutableList<Interceptor> {
        val interceptors: MutableList<Interceptor> = mutableListOf()
        interceptors.add(getHttpLoggingInterceptor())
        interceptors.add(Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $authorization")
                .addHeader("X-Company-Uuid", XCompanyUuid)
                .build()
            chain.proceed(request)
        })

        return interceptors
    }

    private fun getHttpLoggingInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

}