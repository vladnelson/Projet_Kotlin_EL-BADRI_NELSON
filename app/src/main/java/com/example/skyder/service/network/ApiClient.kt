package com.example.skyder.service.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASEURL = "https://www.prevision-meteo.ch/services/json/"

object ApiClient {

    private val gson : Gson by lazy {
        GsonBuilder().setLenient().create()
    }

    private  val httpClient:OkHttpClient by lazy {
        OkHttpClient.Builder().build()
    }

    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASEURL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val apiService : ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }
}