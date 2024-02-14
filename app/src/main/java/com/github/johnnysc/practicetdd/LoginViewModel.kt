package com.github.johnnysc.practicetdd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel(
    private val communication: LoginCommunication,
    private val interactor: LoginInteractor,
    private val mapper: WeatherUiMapper<WeatherUiModel>,
    private val validateEmail: UiValidator,
    private val validatePassword: UiValidator,
    private val dispatchers: DispatchersList
) : ViewModel() {
    fun login(email: String, password: String) = viewModelScope.launch(dispatchers.ui()) {
        val loginState = when {
            !validateEmail.isValid(email) && !validatePassword.isValid(password) -> LoginState.TwoErrors(
                validateEmail.errorMessage(),
                validatePassword.errorMessage()
            )

            !validateEmail.isValid(email) -> LoginState.EmailError(validateEmail.errorMessage())
            !validatePassword.isValid(password) -> LoginState.PasswordError(validatePassword.errorMessage())
            else -> interactor.login().map(mapper).toLoginState()
        }

        communication.map(loginState)

    }

}
