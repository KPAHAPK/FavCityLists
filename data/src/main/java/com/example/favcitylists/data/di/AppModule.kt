package com.example.favcitylists.data.di

import android.content.Context
import androidx.room.Room
import com.example.favcitylists.data.data_source.CityListsDataBase
import com.example.favcitylists.data.data_source.MainDao
import com.example.favcitylists.data.data_source.RoomCallBack
import com.example.favcitylists.repository.room.CityRepository
import com.example.favcitylists.repository.room.use_case.*
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
        val db = Room.databaseBuilder(
            context,
            CityListsDataBase::class.java,
            CityListsDataBase.DATABASE_NAME
        ).addCallback(RoomCallBack(daoProvider))
            .build()
        db.openHelper.writableDatabase
        return db

    }

    @Provides
    fun provideMainDao(database: CityListsDataBase): MainDao {
        return database.mainDao
    }

    @Provides
    @Singleton
    fun provideUseCases(
        repository: CityRepository
    ): UseCases {
        return UseCases(
            GetCustomCityListUseCase(repository),
            GetCustomCityListsUseCase(repository),
            GetCityListUseCase(repository),
            AddCustomCityListUseCase(repository)
        )
    }
}