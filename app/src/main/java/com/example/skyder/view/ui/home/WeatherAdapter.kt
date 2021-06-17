package com.example.skyder.view.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.skyder.domain.WeatherModel
import com.example.skyder.R

class WeatherAdapter(val context: Context?) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    private var data: ArrayList<WeatherModel>? = null

    fun setData(list: ArrayList<WeatherModel>) {
        data = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_weather,parent,false))
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val item = data?.get(position)
        holder.bindView(item)
    }


    class WeatherViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        fun bindView(item: WeatherModel?) {
            //itemView.findViewById<TextView>(R.id.tv_weather_title).text = item?.
            //itemView.findViewById<TextView>(R.id.tv_weather_item_body).text = item?.body

        }
    }
}