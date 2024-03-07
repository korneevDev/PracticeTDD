package com.github.johnnysc.practicetdd

interface Numbers {
    fun minimum(): Int
    fun maximum(): Int

    class Base(private val first: Int, private val second: Int) : Numbers {
        override fun minimum(): Int = first.coerceAtMost(second)

        override fun maximum(): Int = first.coerceAtLeast(second)

    }

    class List(private val list: kotlin.collections.List<Int>) :
        Numbers {
        override fun minimum(): Int = list.minOrNull() ?: Int.MIN_VALUE

        override fun maximum(): Int = list.maxOrNull() ?: Int.MAX_VALUE

    }

}
