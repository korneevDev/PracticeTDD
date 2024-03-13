package com.github.johnnysc.practicetdd

interface LoginInteractor {

    suspend fun login(login: String, password: String): LoginResult
}
