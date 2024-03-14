package com.github.johnnysc.practicetdd

interface ListComparable {
    fun areItemsTheSame(other: ListComparable): Boolean
    fun areContentsTheSame(other: ListComparable): Boolean

    data class Base(private val id: String, private val name: String) : ListComparable {
        override fun areItemsTheSame(other: ListComparable) = this.id == (other as Base).id

        override fun areContentsTheSame(other: ListComparable) = this == other

    }

}
