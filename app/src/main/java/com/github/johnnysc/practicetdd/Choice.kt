package com.github.johnnysc.practicetdd

interface Choice {

    fun init(mediator: MediatorChoice, block: () -> Unit)
    fun isChosen(): Boolean
    fun chose()
    fun rollback()
}
