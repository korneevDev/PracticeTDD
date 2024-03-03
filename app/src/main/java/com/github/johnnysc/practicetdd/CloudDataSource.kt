package com.github.johnnysc.practicetdd

import com.google.gson.Gson

interface CloudDataSource {
    suspend fun fetch() : UserCloud

    class Base(private val apiService: ApiService,
               private val gson: Gson = Gson()
    ) : CloudDataSource {
        override suspend fun fetch() : UserCloud {
            val data = apiService.fetch()

            return if(data.isSuccessful)
                data.body()!!
            else {
                val errorData = gson.fromJson(data.errorBody()!!.string(), ErrorData::class.java)
                throw errorData.toError()
            }
        }

    }

}
