package com.github.johnnysc.practicetdd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

class LoginViewModel(
    private val uiUpdate: LoginUpdate,
    private val validateLogin: ValidateLogin,
    private val validatePassword: ValidatePassword,
    private val interactor: LoginInteractor,
    private val runAsync: RunAsync
) : ViewModel(){
    fun login(login: String, password: String) {
        val backgroundWork : suspend () -> LoginState = {
            try{
                uiUpdate.update(LoginState.Loading)
                validateLogin.validate(login)
                validatePassword.validate(password)
                interactor.login(login, password).map()
            }catch(e: LoginInvalidException){
                val state = LoginState.LoginError(e.message!!)
                uiUpdate.update(state)
                state
            } catch (e: PasswordInvalidException){
                val state = LoginState.PasswordError(e.message!!)
                uiUpdate.update(state)
                state
            }
        }

        runAsync.handleAsync(viewModelScope, backgroundWork){
            uiUpdate.update(it)
        }
    }

}
