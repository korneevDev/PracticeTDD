package com.github.johnnysc.practicetdd

interface ValidationChain {
    suspend fun login(password: String, email: String): LoginState
    fun update(state: LoginState)

    class Process(private val interactor: LoginInteractor, private val uiUpdate: LoginUpdate) :
        ValidationChain {
        override suspend fun login(password: String, email: String) =
            interactor.login(email, password).map().also {
                uiUpdate.update(LoginState.Loading)
            }

        override fun update(state: LoginState) {
            uiUpdate.update(state)
        }


    }

    class Password(
        private val validationText: ValidationText,
        private val uiUpdate: LoginUpdate,
        private val next: ValidationChain
    ) : ValidationChain {
        override suspend fun login(password: String, email: String): LoginState =
            if (validationText.isValid(password) {
                    uiUpdate.update(LoginState.PasswordError(it))
                }) {
                next.login(password, email)
            } else {
                LoginState.Loading
            }

        override fun update(state: LoginState) {
            uiUpdate.update(state)
        }
    }

    class Login(
        private val validationText: ValidationText,
        private val uiUpdate: LoginUpdate,
        private val next: ValidationChain
    ) : ValidationChain {
        override suspend fun login(password: String, email: String) =
            if (validationText.isValid(email) {
                    uiUpdate.update(LoginState.LoginError(it))
                }) {
                next.login(password, email)
            } else {
                LoginState.Loading
            }

        override fun update(state: LoginState) {
            uiUpdate.update(state)
        }
    }

}
