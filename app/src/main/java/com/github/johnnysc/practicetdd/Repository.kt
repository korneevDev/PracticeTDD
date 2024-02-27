package com.github.johnnysc.practicetdd

interface Repository {
    class Base(private val api: Api) {
        fun fetch(value: String, callback: DataCallback) {
            api.fetch(Api.RequestBody(value), object : Api.Callback{
                override fun provideError(error: Api.Result.Error) {
                    callback.provideError(error.getMessage())
                }

                override fun provideSuccess(success: Api.Result.Success) {
                    callback.provideSuccess(success.getMessage())
                }
            })
        }

    }

    interface DataCallback {
        fun provideSuccess(data: String)

        fun provideError(message: String)
    }


}

interface Api {

    fun fetch(body: RequestBody, callback: Callback)
    class RequestBody(private val value: String) {
        fun isEmpty() = value.isNotEmpty()

    }

    interface Callback {
        fun provideError(error: Result.Error)

        fun provideSuccess(success: Result.Success)
    }

    sealed interface Result {

        fun getMessage(): String
        data class Error(private val e: Exception) : Result {
            override fun getMessage() = e.message ?: ""
        }

        data class Success(private val data: String) : Result{
            override fun getMessage() = data

        }

    }

}

