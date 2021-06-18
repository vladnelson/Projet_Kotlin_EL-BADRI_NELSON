package com.example.skyder.service.network


import com.example.skyder.domain.WeatherModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("{ville}")
    suspend fun GetweatherCurrent(@Path("ville") ville:String) : Response<WeatherModel>

    @GET("lat={lat}lng={lng}")
    suspend fun GetweatherLocate(@Path("lat") lat:String, @Path("lng") lng :String ) : Response<WeatherModel>
}