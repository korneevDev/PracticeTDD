package com.github.johnnysc.practicetdd

import kotlinx.coroutines.CoroutineScope

interface RunAsync {

    fun <T : Any> handleAsync(
        coroutineScope: CoroutineScope,
        backgroundBlock: suspend () -> T,
        uiBlock: (T) -> Unit
    )
}
