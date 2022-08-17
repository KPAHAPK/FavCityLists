package com.example.favtownlists.data.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.favtownlists.data.data_source.AppDataBase
import com.example.favtownlists.data.data_source.city.CityEntity
import com.example.favtownlists.data.data_source.city.InitCityList
import com.example.favtownlists.data.data_source.citylist.InitCityListInfo
import com.example.favtownlists.data.data_source.crossref.CustomListCrossRef
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
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
                        provideAppDatabase(app).mainDao().apply {
                            insertCities(InitCityList.initEuropeCityList)
                            insertCityListInfo(InitCityListInfo.initCityListInfo)
                            val cityList: List<CityEntity> =
                                getCities().flatMapConcat { it.asFlow() }.toList()
                            val cityListInfo =
                                getCityListInfo(InitCityListInfo.initCityListInfo.name)
                            for (city in cityList) {
                                insertCrossRef(CustomListCrossRef(city.id, cityListInfo.id))
                            }
                            insertCities(InitCityList.intiOtherCityList)
                        }

                    }
                }
            })
            .build()
    }
}