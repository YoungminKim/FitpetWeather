package com.example.fitpetweather.view.activity

import android.os.Bundle
import androidx.activity.viewModels
import com.example.fitpetweather.AppConst
import com.example.fitpetweather.R
import com.example.fitpetweather.databinding.ActivityWeatherListBinding
import com.example.fitpetweather.view.adapter.WeatherListAdapter
import com.example.fitpetweather.view.base.BaseActivity
import com.example.fitpetweather.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherListActivity: BaseActivity<ActivityWeatherListBinding>(R.layout.activity_weather_list) {

    private val viewModel: WeatherViewModel by viewModels()

    private val adapter: WeatherListAdapter by  lazy {
        WeatherListAdapter(this@WeatherListActivity)
    }

    private var index = 0
    private val test= "test"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding){
            rv.adapter = adapter
        }

        with(viewModel){
            loadData()
            onWeatherData.observe(this@WeatherListActivity){
                it?.list?.let { list ->
                    adapter.addData(AppConst.SEARCH_CITIES[index], list)
                    ++index
                    if(index < AppConst.SEARCH_CITIES.size) loadData(AppConst.SEARCH_CITIES[index])

                }
            }
        }
    }
}