package com.github.johnnysc.practicetdd

interface Numbers {
    fun isSumLong(): Boolean
    fun sumInt(): Int
    fun sumLong(): Long
    fun difference(): Int
    fun divide(): Double

    class Base(
        private val number1: Int,
        private val number2: Int,
        private val summator: SumNumbers = SumNumbers.Base()
    ) : Numbers {
        private lateinit var isLong: STATE
        override fun isSumLong(): Boolean {
            val sum = number1.toLong() + number2
            val result = (sum > Int.MAX_VALUE || sum < Int.MIN_VALUE).also {
                isLong = if (it) STATE.LONG else STATE.INT
            }
            return result
        }

        override fun sumInt(): Int = handle(number1, number2)

        override fun sumLong(): Long = handle(number1.toLong(), number2.toLong())

        override fun difference(): Int = number1 - number2

        private fun <T : Number> handle(number1: T, number2: T) : T{
            return try {
                summator.sum(number1, number2, isLong.get())
            } catch (e: UninitializedPropertyAccessException) {
                throw IllegalAccessException()
            }
        }

        override fun divide(): Double {
            if (number2 == 0)
                throw IllegalArgumentException()

            return number1.toDouble() / number2
        }

    }

}

interface SumNumbers {

    fun <T : Number> sum(num1: T, num2: T, isLong: Boolean): T

    class Base : SumNumbers {
        override fun <T : Number> sum(num1: T, num2: T, isLong: Boolean): T {
            if (num1 is Long && isLong)
                return (num1.toLong() + num2.toLong()) as T

            if (num1 is Int && !isLong)
                return (num1.toInt() + num2.toInt()) as T

            throw IllegalStateException()
        }
    }
}

enum class STATE(private val state: Boolean) {
    LONG(true),
    INT(false);

    fun get() = state
}
