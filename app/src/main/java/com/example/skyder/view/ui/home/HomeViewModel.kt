package com.example.skyder.view.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skyder.domain.WeatherModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    private var weatherRepository: HomeRepository? = null
    var weatherModelListLiveData: LiveData<WeatherModel>? = null
    val text: LiveData<String> = _text

    init {
        weatherRepository = HomeRepository()
        weatherModelListLiveData = MutableLiveData()
    }

}