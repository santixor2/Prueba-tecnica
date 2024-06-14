package com.example.pruebafrogmi.di

import android.content.Context
import androidx.room.Room
import com.example.pruebafrogmi.feature_home.data.local.StoreDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val ADDRESS_DATABASE_NAME = "store_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room
        .databaseBuilder(context, StoreDataBase::class.java, ADDRESS_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideStoreDao(db : StoreDataBase) = db.getStoreDao()
}