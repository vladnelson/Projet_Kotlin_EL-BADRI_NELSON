package com.example.skyder.domain

data class WeatherModel(
    var city_info: City = City(),
    var forecast_info: ForeCast = ForeCast(),
    var current_condition : Condition = Condition()

)


data class City(
    val name: String? = null,
    val country: String? = null,
    val latitude: String? = null,
    val longitude: String? = null,
    val elevation: String? = null,
    val sunrise: String? = null,
    val sunset: String? = null
)

data class ForeCast(
    val elevation: String? = null,
    val longitude: String? = null,
    val latitude: String? = null
)

data class Condition(
    val date: String? = null,
    val hour: String? = null,
    val tmp: String? = null,
    val wnd_spd: String? = null,
    val wnd_gust: String? = null,
    val wnd_dir: String? = null,
    val pressure: String? = null,
    val humidity: String? = null,
    val condition: String? = null,
    val condition_key: String? = null,
    val icon: String? = null,
    val icon_big: String? = null,
)

