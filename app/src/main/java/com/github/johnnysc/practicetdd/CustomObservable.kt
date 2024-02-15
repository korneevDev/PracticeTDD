package com.github.johnnysc.practicetdd

interface CustomObservable<T, U> {
    fun addObserver(observer: U)

    fun update(argument: T)
    fun removeObserver(observer: U)

    class Base<T, U : CustomObserver<T>>(
        private val maxCount: Int = Int.MAX_VALUE,
        private val observers: MutableList<U> = mutableListOf()
    ) : CustomObservable<T, U>{

        override fun addObserver(observer: U) {
            observers.add(observer)
        }

        override fun update(argument: T) {
            if(observers.isEmpty())
                return

            observers.subList(observers.size-maxCount, observers.size).forEach{
                it.update(argument)
            }
        }

        override fun removeObserver(observer: U) {
            observers.remove(observer)
        }


    }

}
