package com.example.skyder.view.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.skyder.domain.WeatherModel
import com.example.skyder.R
import com.example.skyder.domain.WeatherDay

class WeatherAdapter(val cntx: Context?) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    var context : Context? = null
    init {
        this.context = cntx
    }
    private var data: ArrayList<WeatherDay>? = null

    fun setData(list: ArrayList<WeatherDay>) {
        data = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_weather_day,parent,false))
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val item = data?.get(position)
        holder.bindView(item, context)
    }


    class WeatherViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        fun bindView(item: WeatherDay?, context: Context?) {
            itemView.findViewById<TextView>(R.id.tv_day).text = item?.day_long
            itemView.findViewById<TextView>(R.id.tv_condition).text = item?.condition
            Glide.with(context)
                .load(item?.icon_big).into(
                    itemView.findViewById<ImageView>(R.id.img_day)
                )
        }
    }
}