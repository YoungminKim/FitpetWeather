package com.example.fitpetweather.network

import okhttp3.internal.http1.HeadersReader

object UrlInfo {



    const val URL_HEADER = "https://api.openweathermap.org/"
    private const val DATA_DIR = "data/2.5/"
    const val WEATHER_INFO = "${DATA_DIR}forecast"

    const val IMAGE_URL = "https://openweathermap.org/img/wn/"
}