package com.github.johnnysc.practicetdd

class ReviveViewModel(private val communication: ReviveCommunication) {
    fun restore(bundle: SaveAndRestore) {
        communication.map(if (bundle.isEmpty()) ReviveUiState.Initial else bundle.restore())
    }

    fun show(text: String) {
        communication.map(ReviveUiState.Secondary(text))
    }

    fun save(bundle: SaveAndRestore) {
        communication.save(bundle)
    }
}
