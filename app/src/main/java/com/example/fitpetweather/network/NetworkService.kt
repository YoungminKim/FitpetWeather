package com.example.fitpetweather.network

import com.example.fitpetweather.BuildConfig
import com.example.fitpetweather.model.WeatherInfo
import com.example.fitpetweather.network.UrlInfo.WEATHER_INFO
import com.google.gson.GsonBuilder
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.QueryMap
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.HashMap

interface NetworkService {


    @GET(WEATHER_INFO)
    fun getWeatherInfo(@QueryMap params: HashMap<String, Any?>): Single<WeatherInfo>

    companion object {
        const val SUCCESS = 200

        private const val TAG = "NetworkService"
        private const val CONNECT_TIMEOUT = 15L
        private const val WRITE_TIMEOUT = 15L
        private const val READ_TIMEOUT = 15L

        fun create(): NetworkService {

            val okHttpClient = OkHttpClient.Builder().apply {
                connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                retryOnConnectionFailure(true)
                charset(Charsets.UTF_8.name())
                addInterceptor(Interceptor { chain ->

                    chain.proceed(
                        chain.request().newBuilder().apply {

                        }.build()
                    )

                })

                addInterceptor(HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

                })
            }.build()

            return Retrofit.Builder()
                .baseUrl(UrlInfo.URL_HEADER)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
                .create(NetworkService::class.java)
        }

    }
}