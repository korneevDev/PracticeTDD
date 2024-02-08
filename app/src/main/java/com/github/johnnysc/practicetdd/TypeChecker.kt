package com.github.johnnysc.practicetdd

interface TypeChecker {
    fun isType(raw: String): Boolean

    class IsCharacter : TypeChecker {
        override fun isType(raw: String) = raw.length == 1
    }

    class IsBoolean : TypeChecker {
        override fun isType(raw: String) = "true" == raw || raw == "false"
    }

    abstract class AbstractNumber(
        protected val length: Int
    ) : TypeChecker {

        protected abstract fun isInRange(positiveRaw: String, isNegative: Boolean): Boolean
        protected abstract fun isNumericFilter(digit: Char): Boolean
        override fun isType(raw: String): Boolean {

            val isNegative = raw[0] == '-'
            val positiveRaw = if (isNegative) raw.substring(1) else raw

            val isNumeric = positiveRaw.none { !isNumericFilter(it) }

            val isTrueLengthChars = positiveRaw.length <= length

            return isTrueLengthChars && isNumeric && isInRange(positiveRaw, isNegative)
        }
    }

    abstract class IntegerNumber(
        length: Int,
        private val maxNumber: String,
        private val minNumber: String
    ) : AbstractNumber(length){
        override fun isInRange(positiveRaw: String, isNegative: Boolean): Boolean =
            positiveRaw.length < length ||
            positiveRaw.length == length &&
                (isNegative && positiveRaw <= maxNumber ||
                !isNegative && positiveRaw <= minNumber)

        override fun isNumericFilter(digit: Char) = digit.isDigit()
    }

    class IsByte : IntegerNumber(3, "128", "127")

    class IsShort : IntegerNumber(5, "32768", "32767")

    class IsInt : IntegerNumber(10, "2147483648", "2147483647")

    class IsLong : IntegerNumber(19, "9223372036854775808", "9223372036854775807")

    class IsFloat : AbstractNumber(20) {
        override fun isInRange(positiveRaw: String, isNegative: Boolean) = true

        override fun isNumericFilter(digit: Char): Boolean = digit.isDigit() || digit == '.' || digit == 'f'
    }

    class IsDouble : AbstractNumber(100000) {
        override fun isInRange(positiveRaw: String, isNegative: Boolean) = true

        override fun isNumericFilter(digit: Char): Boolean = digit.isDigit() || digit == '.' || digit == 'f'
    }
}