package com.github.johnnysc.practicetdd

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface MessagesCommunication {
    interface Mutable {

        fun map(data: MessageUI)
        fun observe(owner: LifecycleOwner, observer: Observer<List<MessageUI>>)
    }

}
