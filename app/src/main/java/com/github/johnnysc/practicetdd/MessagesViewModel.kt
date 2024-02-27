package com.github.johnnysc.practicetdd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

class MessagesViewModel(
    private val communication: MessagesCommunication.Mutable,
    private val dispatchersList: DispatchersListLaunchers,
    private val viewModelChain: ViewModelChain
) : ViewModel() {
    suspend fun handleInput(message: String) {
        dispatchersList.launchUI(viewModelScope){
            communication.map(MessageUI.User(message))
        }

        dispatchersList.changeToUI {
            dispatchersList.launchBackground(viewModelScope){
                val messageUI = viewModelChain.handle(message)
                communication.map(messageUI)
            }
        }

    }

}
