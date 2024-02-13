package com.github.johnnysc.practicetdd

interface Sorting {
    fun sort(list: List<Int>): List<Int>

    class Base(private val forOut: For, private val forIn: For) : Sorting {
        override fun sort(list: List<Int>): List<Int> {
            val mutableList = list.toMutableList()
            forOut.repeat(list.size){
                var isSwapped = false
                for(index in 0 until mutableList.size-1){
                    if(mutableList[index] > mutableList[index+1]){
                        mutableList[index] = mutableList[index+1].also {
                            mutableList[index+1] = mutableList[index]
                        }
                        isSwapped = true
                    }
                }
                return@repeat !isSwapped
            }

            return mutableList
        }

    }

}
