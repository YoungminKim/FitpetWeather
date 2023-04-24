package com.example.fitpetweather.view.adapter.viewholder

import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.example.fitpetweather.R
import com.example.fitpetweather.databinding.ItemWeatherBinding
import com.example.fitpetweather.model.WeatherInfo
import com.example.fitpetweather.view.base.BaseViewHolder
import splitties.views.backgroundColor
import java.util.*

class WeatherListViewHolder(view: View) : BaseViewHolder<ItemWeatherBinding>(view){
    companion object{
        private const val TAG = "WeatherListViewHolder"
    }
    fun onBind(item: WeatherInfo.Item, isLast: Boolean = false){
        with(binding){
            Log.e(TAG, "city : ${item.city}" )
            if(item.city.isNullOrEmpty()){
                cityRl.visibility = View.GONE
            }else{
                cityRl.visibility = View.VISIBLE
                cityTv.text = item.city
            }

             dividerV.visibility = if(isLast) View.GONE else View.VISIBLE

            val nowCal = Calendar.getInstance()
            nowCal.time = Date(System.currentTimeMillis())

            val today = nowCal.get(Calendar.DATE)
            nowCal.add(Calendar.DATE, 1)
            val tomorrow = nowCal.get(Calendar.DATE)

            val cal = Calendar.getInstance()
            cal.time = Date(item.dt * 1000)

            when(val itemDay = cal.get(Calendar.DATE)){
                today -> dateTv.text = "Today"
                tomorrow -> dateTv.text = "Tomorrow"
                else ->{
                    val week = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.ENGLISH).uppercase()
                    val month = cal.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH).uppercase()
                    dateTv.text = "$week $itemDay $month"
                }

            }

        }
    }
}