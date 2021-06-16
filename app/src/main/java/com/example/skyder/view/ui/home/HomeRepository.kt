package com.example.skyder.view.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.skyder.domain.WeatherModel
import com.example.skyder.service.network.ApiClient
import com.example.skyder.service.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository {

    var apiInterface: ApiInterface? = null;

    fun getweatherCurrent(): LiveData<List<WeatherModel>> {
        val data = MutableLiveData<List<WeatherModel>>()

        apiInterface?.GetweatherCurrent()?.enqueue(object : Callback<List<WeatherModel>> {

            override fun onFailure(call: Call<List<WeatherModel>>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(
                call: Call<List<WeatherModel>>,
                response: Response<List<WeatherModel>>
            ) {
                val res = response.body()

                if (response.code() == 200 && res != null) {
                    data.value = res
                } else {
                    data.value = null
                }

            }
        })

        return data
    }
}