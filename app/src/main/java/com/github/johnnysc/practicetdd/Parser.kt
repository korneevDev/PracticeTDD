package com.github.johnnysc.practicetdd

interface Parser {

    fun parse(raw: String): List<Any>

    class Base(
        private val delimiter: String,
        private val oneItemParser: SingleParser = SingleParser.OneItem()
    ) : Parser {

        init {
            if (delimiter.isEmpty())
                throw IllegalStateException("Delimiter must be not empty")
        }

        override fun parse(raw: String): List<Any> {
            return raw.split(delimiter).filter { it.isNotEmpty() }.map {
                oneItemParser.parse(it)
            }
        }
    }


}

interface SingleParser {
    fun parse(raw: String): Any

    class OneItem(
        private val charChecker: TypeChecker = TypeChecker.IsCharacter(),
        private val boolChecker: TypeChecker = TypeChecker.IsBoolean(),
        private val byteChecker: TypeChecker = TypeChecker.IsByte(),
        private val shortChecker: TypeChecker = TypeChecker.IsShort(),
        private val intChecker: TypeChecker = TypeChecker.IsInt(),
        private val longChecker: TypeChecker = TypeChecker.IsLong(),
        private val floatChecker: TypeChecker = TypeChecker.IsFloat(),
        private val doubleChecker: TypeChecker = TypeChecker.IsDouble()
    ) : SingleParser {
        override fun parse(raw: String): Any {
            if (charChecker.isType(raw))
                return raw[0]

            if (boolChecker.isType(raw))
                return raw == "true"

            if (byteChecker.isType(raw))
                return raw.toByte()

            if (shortChecker.isType(raw))
                return raw.toShort()

            if (intChecker.isType(raw))
                return raw.toInt()

            if (longChecker.isType(raw))
                return raw.toLong()

            if (floatChecker.isType(raw))
                return raw.toFloat()

            if (doubleChecker.isType(raw))
                return raw.toDouble()

            return raw
        }
    }
}
interface ParserMap<T> {
    fun map(data: String): IsEmptyHandleUseCase<T>
}
