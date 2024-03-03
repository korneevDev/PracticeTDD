package com.github.johnnysc.practicetdd

import retrofit2.Response

interface ApiService{

    suspend fun fetch(): Response<UserCloud>
}
