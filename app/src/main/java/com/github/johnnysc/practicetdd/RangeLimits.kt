package com.github.johnnysc.practicetdd

interface RangeLimits {
    fun pair(number: Int): RangePair

    class Base(
        private val list: List<Int>
    ) : RangeLimits {
        override fun pair(number: Int): RangePair {
            val myList = listOf(Border.Base(Int.MIN_VALUE, Long.MIN_VALUE)) + list.map {
                Border.Base(
                    it,
                    it.toLong()
                )
            } + Border.Base(Int.MAX_VALUE, Long.MAX_VALUE)

            for (i in 1 until myList.size) {
                if (myList[i - 1] < number  && myList[i] > number)
                    return RangePair(myList[i - 1].getNumber(), myList[i].getNumber())
                if(myList[i].getNumber() == number){
                    return RangePair(myList[i-1].getNumber(), myList[i+1].getNumber())
                }
            }

            return RangePair(Int.MIN_VALUE, Int.MAX_VALUE)
        }
    }
}

interface Border : Comparable<Int> {

    fun getNumber(): Int
    class Base(
        private val borderValue: Int,
        private val borderMaxValue: Long
    ) : Border {
        override fun compareTo(other:Int): Int {
            return if (borderMaxValue > other)
                1
            else if (borderMaxValue < other)
                -1
            else 0
        }
        override fun getNumber(): Int = borderValue
    }

}

data class RangePair(
    private val left: Int = Int.MIN_VALUE,
    private val right: Int = Int.MAX_VALUE
)
