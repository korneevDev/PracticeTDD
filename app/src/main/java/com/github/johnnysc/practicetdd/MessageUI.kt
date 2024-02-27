package com.github.johnnysc.practicetdd

interface MessageUI {
    data class Base(private val id: Int, private val s: String) : MessageUI

    data class User(private val text: String, private val id: String = "0") : MessageUI
    data class Ai(private val text: String, private val id: String = "1") : MessageUI
    data class AiError(private val text: String, private val id: String) : MessageUI

    object LoadMore : MessageUI

    object LoadPrevious : MessageUI
    object Empty : MessageUI
}
