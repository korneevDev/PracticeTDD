package com.github.johnnysc.practicetdd

interface CustomObject {
    fun <T> checkObserver(observer: CustomObserver<T>) : Boolean
    abstract class Premium : CustomObject{
        override fun <T> checkObserver(observer: CustomObserver<T>): Boolean {
            return observer is CustomObserver.Premium
        }
    }
    abstract class Usual : CustomObject{
        override fun <T> checkObserver(observer: CustomObserver<T>): Boolean {
            return true
        }
    }

}