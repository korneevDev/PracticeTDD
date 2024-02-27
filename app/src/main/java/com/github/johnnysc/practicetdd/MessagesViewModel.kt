package com.github.johnnysc.practicetdd

import androidx.lifecycle.ViewModel

class MessagesViewModel(
    private val communication: MessagesCommunication.Mutable,
    private val dispatchersList: DispatchersList,
    private val viewModelChain: ViewModelChain
) : ViewModel(){
    suspend fun handleInput(message: String) {
        communication.map(MessageUI.User(message))
        val messageUI = viewModelChain.handle(message)
        communication.map(messageUI)
    }

}
