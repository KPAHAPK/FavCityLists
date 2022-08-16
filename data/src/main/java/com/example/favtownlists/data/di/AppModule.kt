package com.example.favtownlists.data.di

import android.app.Application
import androidx.room.Room
import com.example.favtownlists.data.data_source.city.CityDatabase
import com.example.favtownlists.data.data_source.citylist.CityListDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCityDatabase(app: Application): CityDatabase {
        return Room.databaseBuilder(
            app,
            CityDatabase::class.java,
            CityDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideCityListDatabase(app: Application): CityListDatabase {
        return Room.databaseBuilder(
            app,
            CityListDatabase::class.java,
            CityListDatabase.DATABASE_NAME
        ).build()
    }
}