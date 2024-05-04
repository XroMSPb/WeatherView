package ru.xrom.weatherview

import android.graphics.drawable.Drawable


data class Weather(
    val day: String,
    val temperature: Short,
    val imgWeather: Drawable?
)
