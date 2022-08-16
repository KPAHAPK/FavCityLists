package com.example.favtownlists.data.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.favtownlists.data.data_source.city.CityDatabase
import com.example.favtownlists.data.data_source.city.InitCityList
import com.example.favtownlists.data.data_source.citylist.CustomCityListDatabase
import com.example.favtownlists.data.data_source.mappers.toCityEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
        )
            .addCallback(object : RoomDatabase.Callback(){
                override fun onCreate(db: SupportSQLiteDatabase) {
                    CoroutineScope(Dispatchers.IO).launch {
                        provideCityDatabase(app).cityDao()
                            .insertCities(InitCityList().initCityList.map { it.toCityEntity() })
                    }
                }
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideCityListDatabase(app: Application): CustomCityListDatabase {
        return Room.databaseBuilder(
            app,
            CustomCityListDatabase::class.java,
            CustomCityListDatabase.DATABASE_NAME
        ).build()
    }
}