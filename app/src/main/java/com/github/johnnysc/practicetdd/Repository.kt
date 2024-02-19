package com.github.johnnysc.practicetdd

import io.reactivex.Single

interface Repository {
    fun fetch(): Single<String>
}
