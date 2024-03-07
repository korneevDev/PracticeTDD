package com.github.johnnysc.practicetdd

import com.google.gson.annotations.SerializedName

data class ServerException(
    override val message: String,
    private val errorType: String = "UNKNOWN"
) : Exception(message)

data class ErrorData(
    @SerializedName("errorMessage")
    private val errorMessage: String,
    @SerializedName("errorType")
    private val errorType: String?
) {
    fun toError(): ServerException =
        if (errorType == null)
            ServerException(errorMessage)
        else
            ServerException(errorMessage, errorType)
}