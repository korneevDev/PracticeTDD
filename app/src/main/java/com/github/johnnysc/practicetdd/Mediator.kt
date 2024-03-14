package com.github.johnnysc.practicetdd

interface Mediator {
    fun addUser(user: User)
    fun resendMessage(message: String, user: User)

    class Base : Mediator {
        private val usersList = mutableListOf<User>()
        override fun addUser(user: User) {
            usersList.add(user)
        }

        override fun resendMessage(message: String, user: User) {
            usersList.filter { it != user }.forEach {
                it.receive(message)
            }
        }


    }

}
