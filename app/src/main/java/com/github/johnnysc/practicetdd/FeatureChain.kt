package com.github.johnnysc.practicetdd

interface FeatureChain {
    fun canHandle(message: String): Boolean

    suspend fun handle(message: String): MessageUI
    interface CheckAndHandle : FeatureChain

    interface Handle : FeatureChain {
        override fun canHandle(message: String) = true
    }

    class Empty : FeatureChain {
        override fun canHandle(message: String) = false

        override suspend fun handle(message: String): MessageUI = MessageUI.Empty
    }

}
