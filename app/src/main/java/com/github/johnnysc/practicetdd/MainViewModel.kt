package com.github.johnnysc.practicetdd

class MainViewModel(private val liveData: MyObservable<String>) {
    private var value = 0
    fun updateObserver(observer: MyObserver<String>) {
        liveData.setObserver(observer)
    }

    fun go() {
        liveData.setValue((++value).toString())
    }

    fun notifyChanges() {
        liveData.setValue(value.toString())
    }

}
