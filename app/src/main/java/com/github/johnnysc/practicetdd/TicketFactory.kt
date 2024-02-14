package com.github.johnnysc.practicetdd

interface TicketFactory {
    fun ticket(number: Int): LotteryTicket

    class Base : TicketFactory {
        override fun ticket(number: Int): LotteryTicket {
            if (number < 0)
                return LotteryTicket.Fake

            val numberDigits = number.toString().filter { it.isDigit() }

            if (numberDigits.length % 2 != 0 || numberDigits.length > 8)
                return LotteryTicket.Fake

            val firstHalf =
                numberDigits.substring(0, numberDigits.length / 2).map { it.digitToInt() }.sum()

            val secondHalf =
                numberDigits.substring(numberDigits.length / 2).map { it.digitToInt() }.sum()

            return if (firstHalf == secondHalf) LotteryTicket.Winner else LotteryTicket.Loser
        }

    }

}
