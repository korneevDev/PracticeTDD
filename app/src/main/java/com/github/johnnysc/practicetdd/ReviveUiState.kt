package com.github.johnnysc.practicetdd

interface ReviveUiState {
    data class Secondary(private val text: String) : ReviveUiState

    object Initial : ReviveUiState

}
