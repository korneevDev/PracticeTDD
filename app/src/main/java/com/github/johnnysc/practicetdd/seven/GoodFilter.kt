package com.github.johnnysc.practicetdd.seven

interface GoodFilter {

    fun changeSelected(): Boolean
    fun isSelected(): Boolean
    fun map(
        ram: Int,
        os: OS,
        displaySize: Double,
        processor: ProcessorType,
        price: Double
    ): Boolean
    abstract class Abstract(private var state: Boolean = false) : GoodFilter{
        override fun changeSelected(): Boolean = state.also { state = !state }

        override fun isSelected(): Boolean = state
    }


}
