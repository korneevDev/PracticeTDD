package com.github.johnnysc.practicetdd

interface MyObserver<T> {
    fun update(value: T)
    class Empty<T> : MyObserver<T> {
        override fun update(value: T) {}
    }

}
