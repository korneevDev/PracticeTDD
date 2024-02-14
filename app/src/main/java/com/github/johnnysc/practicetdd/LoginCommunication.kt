package com.github.johnnysc.practicetdd

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface LoginCommunication {

    fun map(source: LoginState)

    fun observe(owner: LifecycleOwner, observer: Observer<LoginState>)
}
