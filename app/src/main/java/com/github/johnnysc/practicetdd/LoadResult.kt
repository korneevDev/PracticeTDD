package com.github.johnnysc.practicetdd

interface LoadResult {
    data class Success(private val data: List<Int>) : LoadResult
    data class Error(private val message: String) : LoadResult

}
