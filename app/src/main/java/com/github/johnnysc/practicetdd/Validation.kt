package com.github.johnnysc.practicetdd

interface Validation {

    fun isValid(text: String): Result
    class Password(
        private val minLength: Int = 1,
        private val upperCaseLettersCount: Int = 0,
        private val lowerCaseLettersCount: Int = 0,
        private val specialSignsCount: Int = 0,
        private val numbersCount: Int = 0,
        private val check: Check = Check.SignsCountCheck()
    ) : Validation {

        private val UPPER_CASE_LETTERS = setOf('Q', 'W', 'E', 'R', 'T', 'Y',
                                                'U', 'I', 'O', 'P', 'A', 'S',
                                                'D', 'F', 'G', 'H', 'J', 'K',
                                                'L', 'Z', 'X', 'C', 'V', 'B',
                                                'N', 'M')
        private val LOWER_CASE_LETTERS = setOf('q', 'w', 'e', 'r', 't', 'y',
                                                'u', 'i', 'o', 'p', 'a', 's',
                                                'd', 'f', 'g', 'h', 'j', 'k',
                                                'l', 'z', 'x', 'c', 'v', 'b',
                                                'n', 'm')
        private val SPECIAL_CHARACTERS = setOf('!', '@', '#', '$', '%',
                                            '^', '&', '*', '(', ')', '-', '+', '=', '_', ' ')
        private val NUMBERS = setOf('1', '2', '3', '4', '5', '6', '7', '8', '9', '0')


        init {
            if (minLength < 1 ||
                specialSignsCount < 0 ||
                lowerCaseLettersCount < 0 ||
                upperCaseLettersCount < 0 ||
                numbersCount < 0
            )
                throw IllegalStateException()
        }


        override fun isValid(text: String): Result {
            if (text.length < minLength)
                return Result.MinLengthInsufficient(minLength)

            if (check.check(text, UPPER_CASE_LETTERS) < upperCaseLettersCount)
                return Result.UpperCaseLettersCountInsufficient(upperCaseLettersCount)

            if (check.check(text, LOWER_CASE_LETTERS) < lowerCaseLettersCount)
                return Result.LowerCaseLettersCountInsufficient(lowerCaseLettersCount)

            if (check.check(text, SPECIAL_CHARACTERS) < specialSignsCount)
                return Result.SpecialSignsInsufficient(specialSignsCount)

            if (check.check(text, NUMBERS) < numbersCount)
                return Result.NumbersCountInsufficient(numbersCount)

            return Result.Valid
        }

    }


    sealed interface Result {
        object Valid : Result
        data class MinLengthInsufficient(
            private val minLength: Int
        ) : Result

        data class UpperCaseLettersCountInsufficient(
            private val upperCaseLettersCount: Int
        ) : Result

        data class LowerCaseLettersCountInsufficient(
            private val lowerCaseLettersCount: Int
        ) : Result

        data class NumbersCountInsufficient(
            private val numbersCount: Int
        ) : Result

        data class SpecialSignsInsufficient(
            private val specialSignsCount: Int
        ) : Result
    }
}

interface Check {
    fun check(text: String, signs: Set<Char>): Int

    class SignsCountCheck : Check {
        override fun check(text: String, signs: Set<Char>): Int {
            return text.toCharArray().filter { signs.contains(it) }.size
        }
    }
}

