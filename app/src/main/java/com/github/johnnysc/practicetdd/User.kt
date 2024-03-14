package com.github.johnnysc.practicetdd

interface User {

    fun send(message: String)

    fun receive(message: String)

    abstract class Abstract(private val mediator: Mediator): User {
        override fun send(message: String) {
            mediator.resendMessage(message, this)
        }
    }


}
