package com.github.johnnysc.practicetdd

interface UiValidator {

    fun errorMessage(): String

    fun isValid(text: String): Boolean
}
