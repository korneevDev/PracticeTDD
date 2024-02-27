package com.github.johnnysc.practicetdd

sealed interface MessageUi {
    data class Base(private val id: Int, private val s: String) : MessageUi

    object LoadMore : MessageUi

    object LoadPrevious : MessageUi
}
