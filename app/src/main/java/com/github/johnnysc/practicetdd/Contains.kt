package com.github.johnnysc.practicetdd

interface Contains {
    fun contains(collection: List<String>, item: String): Boolean

    class Base(private val forWrapper: For) : Contains {
        override fun contains(collection: List<String>, item: String): Boolean {
            var isMatched = false
            forWrapper.repeat(collection.size){ index ->
                (collection[index] == item).also { if (it) isMatched = true}
            }

            return isMatched
        }

    }

}
