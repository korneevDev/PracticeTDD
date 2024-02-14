package com.github.johnnysc.practicetdd

import kotlinx.coroutines.CoroutineDispatcher

interface DispatchersList {
    fun io(): CoroutineDispatcher
    fun ui(): CoroutineDispatcher
}
