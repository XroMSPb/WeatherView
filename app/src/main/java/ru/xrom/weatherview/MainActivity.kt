package ru.xrom.weatherview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout



enum class DaysOfWeek(val str: String) {
    MONDAY("Понедельник"),
    TUESDAY("Вторник"),
    WEDNESDAY("Среда"),
    THURSDAY("Четверг"),
    FRIDAY("Пятница"),
    SATURDAY("Суббота"),
    SUNDAY("Воскресенье")
}

class MainActivity : AppCompatActivity() {
    private val items: MutableList<Weather> = mutableListOf()
    lateinit var adapter: WeatherAdapter
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        val swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout);
        recyclerView.layoutManager = LinearLayoutManager(this)


        val onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(item: Weather) {
                val position = items.indexOf(item)
                items.remove(item)
                adapter.notifyItemRemoved(position)
                adapter.notifyItemRangeChanged(position, items.size)
            }
        }
        addItems(100, onItemClickListener)
        adapter = WeatherAdapter(items, onItemClickListener)
        recyclerView.adapter = adapter


        // SetOnRefreshListener on SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            addItems(100, onItemClickListener)
        }
    }

    private fun addItems(count: Int, onItemClickListener: OnItemClickListener) {
        items.clear()
        repeat(count) {
            for (j in DaysOfWeek.entries) {
                items.add(
                    Weather(
                        j.str,
                        (-40..40).random().toShort(),
                        getDrawable(if (j.ordinal % 2 == 0) R.drawable.ic_stat_cloud else R.drawable.ic_stat_cloud_off)
                    )
                )
            }
        }
        adapter = WeatherAdapter(items, onItemClickListener)
        recyclerView.adapter = adapter
    }
}