package com.example.fitpetweather.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpetweather.R
import com.example.fitpetweather.model.WeatherInfo
import com.example.fitpetweather.view.adapter.viewholder.WeatherListViewHolder
import java.text.SimpleDateFormat
import kotlin.math.abs

class WeatherListAdapter(private val context: Context): RecyclerView.Adapter<WeatherListViewHolder>() {

    companion object{
        private val timeFormat = SimpleDateFormat("HH:mm:ss")
    }

    private val inflater: LayoutInflater by lazy {
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }


    private val mList = arrayListOf<WeatherInfo.Item>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = WeatherListViewHolder(
        inflater.inflate(R.layout.item_weather, parent, false))

    override fun getItemCount() = mList.size

    override fun onBindViewHolder(holder: WeatherListViewHolder, position: Int) {
        holder.binding.item = mList[position]
        holder.onBind(mList[position], mList.size - 1 == position)
    }

    fun addData(city : String, data: ArrayList<WeatherInfo.Item>){
        val nowTime = System.currentTimeMillis()
        val nowTimeStr = timeFormat.format(nowTime).replace(":", "")
        val nowTimeInt = Integer.parseInt(nowTimeStr)
        var min = Long.MAX_VALUE

        var absTime: String? = null

        data.map {

            it.dateStr.let { dateStr ->
                val dateArr = dateStr.split(" ")
                val timeStr = dateArr[1].replace(":", "")
                val timeInt = Integer.parseInt(timeStr)

                val abs = abs(timeInt - nowTimeInt)
                if(abs < min){
                    min = abs.toLong()
                    absTime = dateArr[1]
                }else if(!absTime.isNullOrEmpty()) return@map
            }
        }

        val tempList = arrayListOf<WeatherInfo.Item>()

        data.filter { it.dateStr.split(" ")[1] == absTime }.map {
            tempList.add(it)
        }

        tempList[0].city = city

        mList.addAll(tempList)

        notifyItemInserted(mList.size)
    }
}