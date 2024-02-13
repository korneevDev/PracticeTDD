package com.github.johnnysc.practicetdd.seven

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface Communication<T> {

    fun map(source: T)

    fun observe(owner: LifecycleOwner, observer: Observer<T>)

}
