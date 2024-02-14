package com.github.johnnysc.practicetdd

interface WeatherUiMapper<T> {
    fun map(feelsLike: Int, description: String, temp: Int): T

    fun map(exceptionType: ExceptionType): T
}
