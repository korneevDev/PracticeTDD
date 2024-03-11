package com.github.johnnysc.practicetdd

interface MyNode {
    fun hasParent(): Boolean

    fun buildNext(id: Int, value: String): MyNode

    class Builder {

        private var value = ""
        private var parent: MyNode = Empty
        private var id = 0
        fun build(): MyNode = parent.buildNext(id++, value).also { parent = it }

        fun value(value: String): Builder = this.apply {
                this.value = value
            }

    }

    data class Head(
        private val id: Int,
        private val value: String
    ) : MyNode {
        override fun hasParent(): Boolean = false
        override fun buildNext(id: Int, value: String): MyNode = Base(id, this, value)
    }

    data class Base(
        private val id: Int,
        private val parent: MyNode,
        private val value: String
    ) : MyNode {
        override fun hasParent(): Boolean = true
        override fun buildNext(id: Int, value: String): MyNode = Base(id, this, value)

    }

    object Empty : MyNode {
        override fun hasParent(): Boolean = false

        override fun buildNext(id: Int, value: String): MyNode = Head(id, value)
    }

}
