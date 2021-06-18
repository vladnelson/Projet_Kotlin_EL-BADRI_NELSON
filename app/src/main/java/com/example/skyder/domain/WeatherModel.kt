package com.example.skyder.domain

data class WeatherModel(
    var city_info: City = City(),
    var forecast_info: ForeCast = ForeCast(),
    var current_condition: Condition = Condition(),

    val fcst_day_0: WeatherDay? = null,
    val fcst_day_1: WeatherDay? = null,
    val fcst_day_2: WeatherDay? = null,
    val fcst_day_3: WeatherDay? = null,
    val fcst_day_4: WeatherDay? = null,
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
    val latitude: String? = null,

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


data class WeatherDay (
    val date: String? = null,
    val day_short: String? = null,
    val day_long: String? = null,
    val tmin: String? = null,
    val tmax: String? = null,
    val condition: String? = null,
    val condition_key: String? = null,
    val icon: String? = null,
    val icon_big: String? = null,
)