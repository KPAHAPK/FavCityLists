package com.example.favtownlists.data.data_source.di

import com.example.favtownlists.data.data_source.repository.CityListRepositoryImpl
import com.example.favtownlists.data.data_source.repository.CityRepositoryImpl
import com.example.favtownlists.repository.room.CityListRepository
import com.example.favtownlists.repository.room.CityRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCityListRepository(cityListRepositoryImpl: CityListRepositoryImpl): CityListRepository

    @Binds
    @Singleton
    abstract fun bindCityRepository(cityRepositoryImpl: CityRepositoryImpl): CityRepository
}