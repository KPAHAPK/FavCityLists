package com.example.favtownlists.data.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.favtownlists.data.data_source.CityListsDataBase
import com.example.favtownlists.data.data_source.MainDao
import com.example.favtownlists.data.data_source.RoomCallBack
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        daoProvider: Provider<MainDao>
    ): CityListsDataBase {
        return Room.databaseBuilder(
            context,
            CityListsDataBase::class.java,
            "citylistdb"
        )
//            .addCallback(
//                object : RoomDatabase.Callback() {
//                override fun onCreate(db: SupportSQLiteDatabase) {
//                    super.onCreate(db)
//                    CoroutineScope(SupervisorJob()).launch(Dispatchers.IO) {
//                        daoProvider.get().apply {
//                            insertCities(InitCityList.initEuropeCityList)
//                            insertCityListInfo(InitCityListInfo.initCityListInfo)
//                            val cityList: List<CityEntity> =
//                                getCities().flatMapConcat { it.asFlow() }.toList()
//                            val cityListInfo: Flow<CityListInfoEntity> =
//                                getCityListInfo(InitCityListInfo.initCityListInfo.name).onEach {
//
//                                }
//                            for (city in cityList) {
//                                insertCrossRef(CustomListCrossRef(city.id!!, cityListInfo.id!!))
//                            }
//                            insertCities(InitCityList.intiOtherCityList)
//                        }
//                    }
//                }
//            })
            .addCallback(RoomCallBack(daoProvider))
            .build()
    }

    @Provides
    fun provideMainDao(database: CityListsDataBase): MainDao {
        return database.mainDao
    }
}