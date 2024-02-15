package com.github.johnnysc.practicetdd

interface FeatureChain {


    suspend fun handle(message: String): MessageUI
    interface CheckAndHandle : FeatureChain{
        fun canHandle(message: String): Boolean
    }

    interface Handle : FeatureChain

    class Empty : FeatureChain {
        override suspend fun handle(message: String): MessageUI = MessageUI.Empty
    }

}
