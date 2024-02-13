package com.github.johnnysc.practicetdd

interface LotteryTicket {
    fun isFake(): Boolean
    fun isWinner(): Boolean

    object Fake : LotteryTicket {
        override fun isFake() = true

        override fun isWinner() = false
    }

    object Winner : LotteryTicket {
        override fun isFake() = false

        override fun isWinner() = true
    }

    object Loser : LotteryTicket {
        override fun isFake() = false

        override fun isWinner() = false
    }
}
