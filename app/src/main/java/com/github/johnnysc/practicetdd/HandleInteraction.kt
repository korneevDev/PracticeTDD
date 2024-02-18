package com.github.johnnysc.practicetdd

data class HandleInteraction(private val text: String, private val interaction: Interaction) : () -> Unit {
    override fun invoke() {
        interaction.print(text)
    }

}
