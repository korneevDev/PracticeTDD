package com.github.johnnysc.practicetdd.seven

interface Good {

    fun <T> map(mapper: Mapper<T>): T

    fun check(filter: GoodFilter): Boolean
    interface Mapper<T> {
        fun map(ram: Int, os: OS, displaySize: Double, processor: ProcessorType, price: Double): T
    }

}
