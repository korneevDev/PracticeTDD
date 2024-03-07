package com.github.johnnysc.practicetdd

import android.annotation.SuppressLint

class MainViewModelRx(
    private val repository: RepositoryRx,
    private val communication: CommunicationRx,
    private val schedulersList: SchedulersList
) {
    @SuppressLint("CheckResult")
    fun fetch() {
        repository.fetch()
            .subscribeOn(schedulersList.io())
            .observeOn(schedulersList.ui())
            .subscribe({
                communication.map(it)
            }, {
                communication.map(it.message!!)
            })
    }
}
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
