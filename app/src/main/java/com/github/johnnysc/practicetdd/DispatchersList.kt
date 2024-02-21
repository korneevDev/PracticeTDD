package com.github.johnnysc.practicetdd

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

interface DispatchersList {

    fun launchUI(scope: CoroutineScope, block: suspend CoroutineScope.() -> Unit): Job
    fun launchBackground(scope: CoroutineScope, block: suspend CoroutineScope.() -> Unit): Job
    suspend fun changeToUI(block: suspend CoroutineScope.() -> Unit)
}
