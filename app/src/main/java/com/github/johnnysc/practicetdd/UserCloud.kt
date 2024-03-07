package com.github.johnnysc.practicetdd

interface UserCloud {
    data class Base(
        private val name: String,
        private val id: Int
    ) : UserCloud

}
