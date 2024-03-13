package com.github.johnnysc.practicetdd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

class LoginViewModel(
    private val validationChain: ValidationChain,
    private val runAsync: RunAsync
) : ViewModel(){
    fun login(password: String, email: String) {
        val backgroundWork : suspend () -> LoginState ={
            validationChain.login(password, email)
        }

        runAsync.handleAsync(viewModelScope, backgroundWork){
            validationChain.update(it)
        }
    }

}
