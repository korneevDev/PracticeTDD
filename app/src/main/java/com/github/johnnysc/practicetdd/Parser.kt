package com.github.johnnysc.practicetdd

interface Parser<T> {
    fun map(data: String): IsEmptyHandleUseCase<T>
}
