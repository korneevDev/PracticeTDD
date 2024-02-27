package com.github.johnnysc.practicetdd

interface PagingRepository {
    enum class Strategy {
        INIT,
        NEXT,
        PREVIOUS
    }

    fun messages(strategy: Strategy): List<MessageDomain>
}
