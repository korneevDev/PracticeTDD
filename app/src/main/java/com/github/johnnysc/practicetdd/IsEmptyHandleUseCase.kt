package com.github.johnnysc.practicetdd


interface UseCase<T>{
    suspend fun handle(useCase: T): MessageUI
}

interface IsEmptyHandleUseCase<T> : UseCase<T>{
    class Empty <T>: IsEmptyHandleUseCase<T> {
        override suspend fun handle(useCase: T): MessageUI = MessageUI.Empty
        override fun equals(other: Any?): Boolean = true
        override fun hashCode(): Int {
            return javaClass.hashCode()
        }

    }

}
