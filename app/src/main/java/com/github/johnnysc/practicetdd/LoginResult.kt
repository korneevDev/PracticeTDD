package com.github.johnnysc.practicetdd

interface LoginResult {
    fun map(): LoginState

    data class Failed(private val message: String) : LoginResult {
        override fun map() = LoginState.Failed(message)
    }

    object Success : LoginResult {
        override fun map() = LoginState.Success
    }

}
