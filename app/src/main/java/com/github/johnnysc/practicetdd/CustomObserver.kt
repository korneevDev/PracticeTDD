package com.github.johnnysc.practicetdd

interface CustomObserver<T> {

    abstract class Usual<T> : CustomObserver<T>

    abstract class Premium<T> : CustomObserver<T>


    fun update(obj: T)

}
