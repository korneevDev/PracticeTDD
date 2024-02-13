package com.github.johnnysc.practicetdd.seven

class MainViewModel(
    private val filters: MutableList<GoodFilter>,
    private val products: MutableList<Good>,
    private val communication: Communication<List<Good>>,
    private val filtersCommunication: Communication<List<GoodFilter>>
) {
    fun change(filter: GoodFilter) {
        filters.forEach{
            if (it == filter)
                it.changeSelected()
        }

        val selectedProducts = products.filter {good ->
            filters.forEach{
                if(it.isSelected() && !good.check(it))
                    return@filter false
            }
            true
        }

        communication.map(selectedProducts)

    }

}
