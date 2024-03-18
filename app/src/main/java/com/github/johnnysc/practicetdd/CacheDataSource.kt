package com.github.johnnysc.practicetdd

interface CacheDataSource {

    suspend fun load(): List<Int>
    suspend fun save(data: List<Int>)
}
