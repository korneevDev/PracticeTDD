package com.github.johnnysc.practicetdd

data class WeatherUiModel(
    private val description: String ="",
    private val isError: Boolean = false
) {
    fun toLoginState(): LoginState =
        if(isError)
            LoginState.Error(this)
        else
            LoginState.Success(this)

}
