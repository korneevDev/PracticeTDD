package com.github.johnnysc.practicetdd

data class LegacyObject(private val lambda: () -> Unit, private val text: String) {
    fun go() {
        lambda.invoke()
    }
}
