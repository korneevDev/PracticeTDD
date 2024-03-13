package com.github.johnnysc.practicetdd

interface LoginState {
    object Loading : LoginState
    object Success : LoginState

    data class LoginError(private val message: String) : LoginState

    data class PasswordError(private val message: String) : LoginState
    data class Failed(private val message: String) : LoginState

}
