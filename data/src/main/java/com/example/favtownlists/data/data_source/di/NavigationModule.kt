package com.example.favtownlists.data.data_source.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Provides
    @Singleton
    fun provideCicerone(): Cicerone<Router> = Cicerone.create()

    @Provides
    fun provideRouter(cicerone: Cicerone<Router>): Router = cicerone.router

    @Provides
    fun provideNavigationHolder(cicerone: Cicerone<Router>): NavigatorHolder = cicerone.getNavigatorHolder()

}