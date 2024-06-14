package com.example.pruebafrogmi.di

import android.content.Context
import com.example.pruebafrogmi.core.service.InterceptorData
import com.example.pruebafrogmi.core.service.RetrofitClient
import com.example.pruebafrogmi.feature_home.data.repository.FrogmiApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext context: Context): Retrofit {
        val authorization = ""
        val XCompanyUuid = ""
        return RetrofitClient.getClient(InterceptorData.getInterceptorData(context = context,authorization, XCompanyUuid))!!
    }

    @Provides
    @Singleton
    fun frogmiApi(retrofit: Retrofit):FrogmiApi{
        return retrofit.create(FrogmiApi::class.java)
    }
}