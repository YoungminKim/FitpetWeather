package com.example.fitpetweather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fitpetweather.AppConst
import com.example.fitpetweather.AppConst.SEARCH_CITIES
import com.example.fitpetweather.model.WeatherInfo
import com.example.fitpetweather.repository.WeatherRepository
import com.example.fitpetweather.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository): BaseViewModel() {

    companion object{
        private const val UNIT_METRIC = "metric"
    }

    private val _onWeatherData = MutableLiveData<WeatherInfo?>()
    val onWeatherData : LiveData<WeatherInfo?>
        get() = _onWeatherData


    fun loadData(city: String = SEARCH_CITIES[0]){
        val params = hashMapOf<String, Any?>()
        params.apply {
            put("q", city)
            put("units", UNIT_METRIC)
            put("APPID", AppConst.API_KEY)
        }
        addToDisposable(weatherRepository.getWeather(params = params).subscribe({
            _onWeatherData.postValue(it)
        },{ e ->
            e.printStackTrace()
        }))
    }
}