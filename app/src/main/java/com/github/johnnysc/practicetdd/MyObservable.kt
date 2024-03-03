package com.github.johnnysc.practicetdd

interface MyObservable<T> {
    fun setValue(value: T)
    fun setObserver(observer: MyObserver<T>)

    class SingleLiveEvent<T> : MyObservable<T> {
        private var observer : MyObserver<T> = MyObserver.Empty()
        private val values = mutableListOf<T>()
        override fun setValue(value: T) {
            if(value !in values) {
                observer.update(value)
                values.add(value)
            }

        }

        override fun setObserver(observer: MyObserver<T>) {
            this.observer = observer
            values.clear()

        }

    }

    class Base<T> : MyObservable<T> {
        private var observer : MyObserver<T> = MyObserver.Empty()
        override fun setValue(value: T) {
            observer.update(value)
        }

        override fun setObserver(observer: MyObserver<T>) {
            this.observer = observer
        }


    }

}
