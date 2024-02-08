package com.github.johnnysc.practicetdd

interface TypeChecker {
    fun isType(raw: String): Boolean

    class IsCharacter : TypeChecker {
        override fun isType(raw: String) = raw.length == 1
    }

    class IsBoolean : TypeChecker {
        override fun isType(raw: String) = "true" == raw || raw == "false"
    }

    abstract class AbstractNumber : TypeChecker {
        protected abstract val length: Int

        abstract fun isInRange(positiveRaw: String, isNegative: Boolean): Boolean
        override fun isType(raw: String): Boolean {

            val isNegative = raw[0] == '-'
            val positiveRaw = if (isNegative) raw.substring(1) else raw

            val isNumeric = positiveRaw.none { !it.isDigit() }


            val isTrueLengthChars = positiveRaw.length <= length


            return isTrueLengthChars && isNumeric && isInRange(positiveRaw, isNegative)
        }
    }

    class IsByte() : AbstractNumber() {
        override val length: Int = 3
        override fun isInRange(positiveRaw: String, isNegative: Boolean): Boolean {
            return positiveRaw.length < 3 ||
                    (positiveRaw.length == 3 &&
                    (positiveRaw[0] < '1' || (positiveRaw[0] == '1' &&
                    (positiveRaw[1] < '2' || (positiveRaw[1] == '2' &&

                        (isNegative && positiveRaw[2] <= '8' ||
                        !isNegative && positiveRaw[2] <= '7'))))))
        }
    }

    class IsShort : AbstractNumber() {
        override val length: Int = 5
        override fun isInRange(positiveRaw: String, isNegative: Boolean): Boolean {
            return positiveRaw.length < 5 ||
                    (positiveRaw.length == 5 &&
                            positiveRaw[0] < '3' || (positiveRaw[0] == '3' &&
                            positiveRaw[1] < '2' || (positiveRaw[1] == '2' &&
                            positiveRaw[2] < '7' || (positiveRaw[2] == '7' &&
                            positiveRaw[3] < '6' || (positiveRaw[3] == '6' &&

                            (isNegative && positiveRaw[4] <= '8' ||
                                    !isNegative && positiveRaw[4] <= '7'))))))
        }
    }

    class IsInt : AbstractNumber() {
        override val length: Int = 10
        override fun isInRange(positiveRaw: String, isNegative: Boolean): Boolean {
            return positiveRaw.length < 10 ||
                    (positiveRaw.length == 10 &&
                            positiveRaw[0] < '2' || (positiveRaw[0] == '2' &&
                            positiveRaw[1] < '1' || (positiveRaw[1] == '1' &&
                            positiveRaw[2] < '4' || (positiveRaw[2] == '4' &&
                            positiveRaw[3] < '7' || (positiveRaw[3] == '7' &&
                            positiveRaw[4] < '4' || (positiveRaw[4] == '4' &&
                            positiveRaw[5] < '8' || (positiveRaw[5] == '8' &&
                            positiveRaw[6] < '3' || (positiveRaw[6] == '3' &&
                            positiveRaw[7] < '6' || (positiveRaw[7] == '6' &&
                            positiveRaw[8] < '4' || (positiveRaw[8] == '4' &&

                            (isNegative && positiveRaw[9] <= '8' ||
                                    !isNegative && positiveRaw[9] <= '7')))))))))))
        }
    }

    class IsLong : AbstractNumber() {
        override val length: Int = 19
        override fun isInRange(positiveRaw: String, isNegative: Boolean): Boolean {
            return positiveRaw.length < 19 ||
                    (positiveRaw.length == 19 &&
                            positiveRaw[0] < '9' || (positiveRaw[0] == '9' &&
                            positiveRaw[1] < '2' || (positiveRaw[1] == '2' &&
                            positiveRaw[2] < '2' || (positiveRaw[2] == '2' &&
                            positiveRaw[3] < '3' || (positiveRaw[3] == '3' &&
                            positiveRaw[4] < '3' || (positiveRaw[4] == '3' &&
                            positiveRaw[5] < '7' || (positiveRaw[5] == '7' &&
                            positiveRaw[6] < '2' || (positiveRaw[6] == '2' &&
                            positiveRaw[7] < '0' || (positiveRaw[7] == '0' &&
                            positiveRaw[8] < '3' || (positiveRaw[8] == '3' &&
                            positiveRaw[9] < '6' || (positiveRaw[9] == '6' &&
                            positiveRaw[10] < '8' || (positiveRaw[10] == '8' &&
                            positiveRaw[11] < '5' || (positiveRaw[11] == '5' &&
                            positiveRaw[12] < '4' || (positiveRaw[12] == '4' &&
                            positiveRaw[13] < '7' || (positiveRaw[13] == '7' &&
                            positiveRaw[14] < '7' || (positiveRaw[14] == '7' &&
                            positiveRaw[15] < '5' || (positiveRaw[15] == '5' &&
                            positiveRaw[16] < '8' || (positiveRaw[16] == '8' &&
                            positiveRaw[17] < '0' || (positiveRaw[17] == '0' &&

                            (isNegative && positiveRaw[18] <= '8' ||
                                    !isNegative && positiveRaw[18] <= '7'))))))))))))))))))))
        }
    }

    class IsFloat: TypeChecker {
        override fun isType(raw: String): Boolean {
            val isNegative = raw[0] == '-'
            val positiveRaw = if (isNegative) raw.substring(1) else raw

            val isNumeric = positiveRaw.none { !(it.isDigit() || it == '.' || it == 'f')}

            return isNumeric && positiveRaw.length <= 20
        }
    }

    class IsDouble: TypeChecker {
        override fun isType(raw: String): Boolean {
            val isNegative = raw[0] == '-'
            val isNumeric =
                (if (isNegative) raw.substring(1) else raw)
                    .none { !(it.isDigit() || it == '.')}

            return isNumeric
        }
    }

}