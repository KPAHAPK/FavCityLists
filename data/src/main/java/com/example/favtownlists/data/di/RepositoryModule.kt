package com.example.favtownlists.data.di

import com.example.favtownlists.data.repository.CityListInfoRepositoryImpl
import com.example.favtownlists.data.repository.CityListRepositoryImpl
import com.example.favtownlists.repository.room.CityListInfoRepository
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
    abstract fun bindCityListRepository(cityListRepositoryImpl: CityListInfoRepositoryImpl): CityListInfoRepository

    @Binds
    @Singleton
    abstract fun bindCityRepository(cityRepositoryImpl: CityListRepositoryImpl): CityRepository
}