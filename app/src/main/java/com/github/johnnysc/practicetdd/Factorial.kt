package com.github.johnnysc.practicetdd

typealias BigInt = java.math.BigInteger
interface Factorial<T> {

    fun value(number: T): T
    class Int : Factorial<kotlin.Int> {
        override fun value(number: kotlin.Int): kotlin.Int {
            if(number <= 1)
                return 1
            return value(number-1) * number
        }


    }

    class Double : Factorial<kotlin.Double> {
        override fun value(number: kotlin.Double): kotlin.Double {
            if(number <= 1)
                return 1.0
            return value(number-1) * number
        }


    }

    class BigInteger : Factorial<java.math.BigInteger> {
        override fun value(number: java.math.BigInteger): java.math.BigInteger {
            if(number <= BigInt.valueOf(1))
                return BigInt.valueOf(1)
            return value(number-BigInt.valueOf(1)) * number
        }

    }
    class Factory(
        private val int: Factorial<kotlin.Int>,
        private val double: Factorial<kotlin.Double>,
        private val bigInteger: Factorial<java.math.BigInteger>
    ) : Factorial<Number>{
        override fun value(number: Number): Number {
            return when {
                number.toShort() < 0 -> throw IllegalArgumentException()
                number.toShort() <= 12 -> int.value(number.toInt())
                number.toShort() <= 170 -> double.value(number.toDouble())
                number.toShort() <= 11000 -> bigInteger.value(BigInt.valueOf(number.toLong()))
                else -> throw  IllegalStateException()
            }
        }


    }

}
