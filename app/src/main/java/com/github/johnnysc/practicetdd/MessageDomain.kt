package com.github.johnnysc.practicetdd

sealed interface MessageDomain {

    fun <T> map(mapper: Mapper<T>): T
    data class Base(private val id: Int, private val text: String) : MessageDomain {
        override fun <T> map(mapper: Mapper<T>): T = mapper.mapSuccess(id, text)
    }

    object LoadMore : MessageDomain {
        override fun <T> map(mapper: Mapper<T>): T = mapper.mapToNext()
    }

    object LoadPrevious : MessageDomain {
        override fun <T> map(mapper: Mapper<T>): T = mapper.mapToPrevious()
    }
}

interface Mapper<T> {

    fun mapSuccess(id: Int, text: String): T

    fun mapToPrevious() : T

    fun mapToNext(): T
}
