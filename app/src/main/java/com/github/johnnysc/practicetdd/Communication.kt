package com.github.johnnysc.practicetdd

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface Communication {

    fun map(data: List<MessageUI>)
    fun observe(owner: LifecycleOwner, observer: Observer<List<MessageUI>>)
}
