package com.github.johnnysc.practicetdd

interface CloudDataSource {

    suspend fun load(): List<Int>
}
