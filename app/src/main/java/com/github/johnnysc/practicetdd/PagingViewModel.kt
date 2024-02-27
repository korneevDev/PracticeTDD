package com.github.johnnysc.practicetdd

class PagingViewModel(
    private val repository: PagingRepository,
    private val communication: Communication,
    private val mapper: Mapper<MessageUi> = MapperToUI()
) {
    fun init(isFirstRun: Boolean) {
        if (isFirstRun)
            load(PagingRepository.Strategy.INIT)
    }

    fun loadMore() = load(PagingRepository.Strategy.NEXT)

    fun loadPrevious() = load(PagingRepository.Strategy.PREVIOUS)

    private fun load(strategy: PagingRepository.Strategy) {
        communication.map(
            repository.messages(strategy)
                .map { it.map(mapper) }
        )
    }

}

class MapperToUI : Mapper<MessageUi> {
    override fun mapSuccess(id: Int, text: String): MessageUi = MessageUi.Base(id, text)
    override fun mapToPrevious(): MessageUi = MessageUi.LoadPrevious
    override fun mapToNext(): MessageUi = MessageUi.LoadMore
}
