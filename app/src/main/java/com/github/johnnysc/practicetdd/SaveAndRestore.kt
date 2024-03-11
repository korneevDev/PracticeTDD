package com.github.johnnysc.practicetdd

interface SaveAndRestore {

    fun isEmpty(): Boolean
    fun save(state: ReviveUiState)
    fun restore(): ReviveUiState
}
