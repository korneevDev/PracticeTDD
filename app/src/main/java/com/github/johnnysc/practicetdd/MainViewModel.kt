package com.github.johnnysc.practicetdd

import android.annotation.SuppressLint

class MainViewModel(
    private val repository: Repository,
    private val communication: Communication,
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
