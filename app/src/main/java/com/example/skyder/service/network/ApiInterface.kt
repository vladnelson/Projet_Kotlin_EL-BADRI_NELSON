package com.example.skyder.service.network


import com.example.skyder.domain.WeatherModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("Paris")
    fun GetweatherCurrent() : Call<List<WeatherModel>>
}