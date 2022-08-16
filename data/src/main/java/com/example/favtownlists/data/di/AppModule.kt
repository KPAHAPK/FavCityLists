package com.example.favtownlists.data.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.favtownlists.data.data_source.AppDataBase
import com.example.favtownlists.data.data_source.InitCustomList
import com.example.favtownlists.data.data_source.city.InitCityList
import com.example.favtownlists.data.data_source.mappers.toCityEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDataBase {
        return Room.databaseBuilder(
            app,
            AppDataBase::class.java,
            AppDataBase.DATABASE_NAME
        )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    CoroutineScope(Dispatchers.IO).launch {
                        val job = launch {
                            provideAppDatabase(app).cityDao()
                                .insertCities(InitCityList.initCityList)
                        }
                        job.join()
                        provideAppDatabase(app).customCityListDao()
                            .insertCustomList(InitCustomList.initCustomList)

                    }
                }
            })
            .build()
    }
}