package com.example.fitpetweather.model

import com.google.gson.annotations.SerializedName

data class WeatherInfo(
    val cod: String,
    val message: String,
    val cnt: Int,
    val list: ArrayList<Item>,
    val city: City,
) {
    data class Item(
        val dt: Long,
        val main: Main,
        val weather: ArrayList<Weather>,
        val clouds: Cloud,
        val wind: Wind,
        val visibility: Int,
        val pop: Float,
        val rain: Rain,
        val snow: Snow,
        val sys: Sys,
        @SerializedName("dt_txt")
        val dateStr: String,
        var city: String?
    )

    data class Rain(
        @SerializedName("3h")
        val h:Float
    )

    data class Snow(
        @SerializedName("3h")
        val h:Float
    )

    data class Cloud(
        val all: Int
    )

    data class Wind(
        val speed: Float,
        val deg: Int,
        val gust: Float
    )

    data class Sys(
        val pod: String
    )
    data class Weather(
        val id: Int,
        val main: String,
        val description: String,
        val icon: String
    )

    data class Main(
        val temp: Float,
        val feels_like: Float,
        val temp_min: Float,
        val temp_max: Float,
        val pressure: Int,
        val sea_level: Int,
        val grnd_level: Int,
        val humidity: Int,
        val temp_kf: Float
    )

    data class City(
        val id: Int,
        val name: String,
        val coord: Coord,
        val country: String,
        val population: Long,
        val timezone: Long,
        val sunrise: Long,
        val sunset: Long
    )

    data class Coord(
        val lon: Double,
        val lat: Double
    )
}