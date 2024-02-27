package com.github.johnnysc.practicetdd

class PagingViewModel(
    private val repository: PagingRepository,
    private val communication: Communication,
    private val mapper: Mapper<MessageUI> = MapperToUI()
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

class MapperToUI : Mapper<MessageUI> {
    override fun mapSuccess(id: Int, text: String): MessageUI = MessageUI.Base(id, text)
    override fun mapToPrevious(): MessageUI = MessageUI.LoadPrevious
    override fun mapToNext(): MessageUI = MessageUI.LoadMore
}
