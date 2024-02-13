package com.github.johnnysc.practicetdd

interface NewInt {
    fun isValid(number: Int): Boolean

    class Positive(
        private val checker: NewInt = Empty()
    ) : NewInt{
        override fun isValid(number: Int): Boolean {
            return number > 0 && checker.isValid(number)
        }

    }

    class Negative : NewInt{
        override fun isValid(number: Int) = number < 0

    }

    class Odd(private val checker: NewInt = Empty()) : NewInt {
        override fun isValid(number: Int) = number % 2 != 0 && checker.isValid(number)

    }

    class Less(private val limit: Int) : NewInt {
        override fun isValid(number: Int) = number < limit

    }

    class Empty : NewInt {
        override fun isValid(number: Int): Boolean = true
    }

}
