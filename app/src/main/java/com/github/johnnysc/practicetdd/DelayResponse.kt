package com.github.johnnysc.practicetdd

import kotlinx.coroutines.delay

interface DelayResponse {
    suspend fun <T> delayAfter(delayInMillis: Long, function: suspend () -> T): T

    class Base(private val now: Now) : DelayResponse {
        override suspend fun <T> delayAfter(delayInMillis: Long, function: suspend () -> T): T{
            val start = now.time()

            val result = function.invoke()

            delay(delayInMillis - (now.time() - start))

            return result
        }

    }

}
