package com.example.fitpetweather.repository

import com.example.fitpetweather.network.NetworkService
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(private val api: NetworkService) {

    fun getWeather(params: HashMap<String, Any?>) = api.getWeatherInfo(params)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}