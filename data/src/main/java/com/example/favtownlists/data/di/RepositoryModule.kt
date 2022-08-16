package com.example.favtownlists.data.di

import com.example.favtownlists.data.repository.CustomCityListRepositoryImpl
import com.example.favtownlists.data.repository.CityListRepositoryImpl
import com.example.favtownlists.repository.room.CustomCityListRepository
import com.example.favtownlists.repository.room.CityListRepository
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
    abstract fun bindCityListRepository(cityListRepositoryImpl: CustomCityListRepositoryImpl): CustomCityListRepository

    @Binds
    @Singleton
    abstract fun bindCityRepository(cityRepositoryImpl: CityListRepositoryImpl): CityListRepository
}