package com.github.johnnysc.practicetdd

class Legacy(private val text: String, private val interaction: Interaction) {

    fun map(): LegacyObject =
        LegacyObject(text = text, lambda = HandleInteraction(text, interaction))
}
