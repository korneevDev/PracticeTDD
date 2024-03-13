package com.github.johnnysc.practicetdd

interface ValidationText {

    fun isValid(text: String, consumeErrorMessage: ConsumeErrorMessage): Boolean
}
