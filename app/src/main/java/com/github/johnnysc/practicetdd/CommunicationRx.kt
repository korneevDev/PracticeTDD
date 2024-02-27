package com.github.johnnysc.practicetdd

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface CommunicationRx {
    fun observe(owner: LifecycleOwner, observer: Observer<String>)
    fun map(data: String)
}
