package com.github.johnnysc.practicetdd

import io.reactivex.Scheduler

interface SchedulersList {
    fun io(): Scheduler
    fun ui(): Scheduler
}
