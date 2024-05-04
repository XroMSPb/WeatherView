package ru.xrom.weatherview

import android.graphics.Color
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val sourceDay: TextView = itemView.findViewById(R.id.day)
    private val sourceTemp: TextView = itemView.findViewById(R.id.temp)
    private val sourceIcon: ImageView = itemView.findViewById(R.id.icon)
    private val sourceFrame: FrameLayout = itemView.findViewById(R.id.frame)

    fun bind(model: Weather) {
        sourceDay.text = model.day
        sourceTemp.text = model.temperature.toString()
        sourceIcon.setImageDrawable(model.imgWeather)
        val tempColor = when (model.temperature) {
            in -100..-40 -> Color.parseColor("#1E88E5")
            in -39..-20 -> Color.parseColor("#42A5F5")
            in -19..-5 -> Color.parseColor("#90CAF9")
            in -4..0 -> Color.parseColor("#E3F2FD")
            in 1..5 -> Color.parseColor("#FFCDD2")
            in 6..19 -> Color.parseColor("#E57373")
            in 20..40 -> Color.parseColor("#F44336")
            else -> Color.parseColor("#D32F2F")
        }
        sourceFrame.setBackgroundColor(tempColor)
    }
}
