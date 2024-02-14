package com.github.johnnysc.practicetdd

interface LoginInteractor {
    suspend fun login(): WeatherItem

}
