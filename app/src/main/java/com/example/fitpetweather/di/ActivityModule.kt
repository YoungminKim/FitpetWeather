package com.example.fitpetweather.di

import com.example.fitpetweather.network.NetworkService
import com.example.fitpetweather.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    @Provides
    fun provideWeatherRepository(api: NetworkService) = WeatherRepository(api = api)
}