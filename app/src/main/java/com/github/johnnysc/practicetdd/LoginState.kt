package com.github.johnnysc.practicetdd

interface LoginState {
    data class EmailError(private val value: String) : LoginState
    data class TwoErrors(private val loginError: String, private val passwordError: String) :
        LoginState
    data class PasswordError(private val value: String) : LoginState
    data class Error(private val value: Any) : LoginState
    data class Success(private val value: WeatherUiModel) : LoginState
}
