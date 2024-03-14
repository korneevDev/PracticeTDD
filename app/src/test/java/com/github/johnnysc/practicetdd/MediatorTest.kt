package com.github.johnnysc.practicetdd

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Also check out Mediator Test in androidTest package with Espresso
 */
class MediatorTest {

    @Test
    fun test_1() {
        val mediator = MediatorChoice.Base()
        val choiceOne = FakeChoice()
        val choiceTwo = FakeChoice()
        var count = 0
        val block: () -> Unit = { count++ }

        mediator.change(choiceOne, block)

        assertEquals(0, choiceOne.rollbackCalledCount)
        assertEquals(1, choiceOne.choseCalledCount)
        assertEquals(1, count)

        mediator.change(choiceTwo, block)

        assertEquals(1, choiceOne.rollbackCalledCount)
        assertEquals(1, choiceOne.choseCalledCount)
        assertEquals(2, count)
        assertEquals(1, choiceTwo.choseCalledCount)
        assertEquals(0, choiceTwo.rollbackCalledCount)

        mediator.change(choiceOne, block)
        assertEquals(1, choiceOne.rollbackCalledCount)
        assertEquals(2, choiceOne.choseCalledCount)
        assertEquals(3, count)
        assertEquals(1, choiceTwo.choseCalledCount)
        assertEquals(1, choiceTwo.rollbackCalledCount)
    }

    @Test
    fun test_2() {
        val mediator: Mediator = Mediator.Base()
        val userOne = FakeUser(id = "1", mediator = mediator)
        val userTwo = FakeUser(id = "2", mediator = mediator)
        val userThree = FakeUser(id = "3", mediator = mediator)
        mediator.addUser(user = userOne)
        mediator.addUser(user = userTwo)
        mediator.addUser(user = userThree)

        userOne.send(message = "message from user one")
        userOne.checkReceiveCalledTimes(times = 0)

        userTwo.checkReceived(message = "message from user one")
        userTwo.checkReceiveCalledTimes(times = 1)
        userThree.checkReceived(message = "message from user one")
        userThree.checkReceiveCalledTimes(times = 1)

        userTwo.send(message = "message from user two")
        userTwo.checkReceiveCalledTimes(times = 1)

        userOne.checkReceived(message = "message from user two")
        userOne.checkReceiveCalledTimes(times = 1)
        userThree.checkReceived(message = "message from user two")
        userThree.checkReceiveCalledTimes(times = 2)

        userThree.send(message = "message from user three")
        userThree.checkReceiveCalledTimes(times = 2)

        userOne.checkReceived(message = "message from user three")
        userOne.checkReceiveCalledTimes(times = 2)
        userTwo.checkReceived(message = "message from user three")
        userTwo.checkReceiveCalledTimes(times = 2)
    }
}

private class FakeChoice : Choice {

    override fun init(mediator: MediatorChoice, block: () -> Unit) = Unit

    override fun isChosen() =
        choseCalledCount != 0 && rollbackCalledCount != 0 && choseCalledCount > rollbackCalledCount

    var choseCalledCount = 0

    override fun chose() {
        choseCalledCount++
    }

    var rollbackCalledCount = 0

    override fun rollback() {
        rollbackCalledCount++
    }
}

private class FakeUser(private val id: String, mediator: Mediator) :
    User.Abstract(mediator = mediator) {

    private var count = 0
    private var receivedMessage: String = ""

    override fun receive(message: String) {
        receivedMessage = message
        count++
    }

    fun checkReceived(message: String) {
        assertEquals(message, receivedMessage)
    }

    fun checkReceiveCalledTimes(times: Int) {
        assertEquals(times, count)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FakeUser

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}