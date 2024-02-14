package com.github.johnnysc.practicetdd

interface Count {
    fun click()

    class Base(private val callback: Callback) : Count {
        private var counter = 0
        override fun click() {
            callback.provide((++counter).toString())
        }

    }

    interface Callback{
        fun provide(value: String)
    }
}
