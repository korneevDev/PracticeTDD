package com.github.johnnysc.practicetdd

interface Command<T> {
    fun canHandle(message: String): Boolean

    suspend fun handle(useCase: T): MessageUI


    //T - interactor
    //U - use case

    abstract class Abstract<T, U>(private val parser: ParserMap<U>) : Command<U> {

        private var currentUseCase: UseCase<U> = IsEmptyHandleUseCase.Empty()

        override fun canHandle(message: String): Boolean =
            parser.map(message).also { currentUseCase = it } !is IsEmptyHandleUseCase.Empty


        override suspend fun handle(useCase: U) =
            currentUseCase.handle(useCase)
    }

}
