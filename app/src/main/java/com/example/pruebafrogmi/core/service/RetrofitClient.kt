package com.example.pruebafrogmi.core.service

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private var retrofit: Retrofit? = null
    const val BASE_URL = "https://api.frogmi.com/api/v3/"

    fun getClient(interceptor: MutableList<Interceptor>): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .client(getOkHttpLog(interceptor))
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
        }
        return retrofit
    }

    private fun getOkHttpLog(interceptor: MutableList<Interceptor>): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
        interceptor.let {
            interceptor.forEach {
                okHttpBuilder.addInterceptor(it)
            }
        }
        return okHttpBuilder.build()
    }
}