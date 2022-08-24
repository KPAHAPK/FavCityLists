package com.example.favcitylists.data.di

import com.example.favcitylists.data.repository.CityListRepositoryImpl
import com.example.favcitylists.repository.room.CityRepository
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
    abstract fun bindCityRepository(cityRepositoryImpl: CityListRepositoryImpl): CityRepository
}