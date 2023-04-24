package com.example.fitpetweather.view.base

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.fitpetweather.network.UrlInfo.IMAGE_URL
import java.io.File




@BindingAdapter(value = ["imgUrl"])
fun setImageResource(v: ImageView, imgUrl: String){
    Glide.with(v.context)
        .load("${IMAGE_URL}$imgUrl@2x.png")
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .into(v)
}


@BindingAdapter("format", "txt")
fun setFormatText(v: TextView, format : String, temp: Float?){
    temp?.let {
        v.text = format.format(it.toInt())
    }

}