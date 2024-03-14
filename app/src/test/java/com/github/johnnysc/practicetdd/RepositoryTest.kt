package com.github.johnnysc.practicetdd

import kotlinx.coroutines.Dispatchers.Unconfined
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class RepositoryTest {

    @Test(timeout = 1500)
    fun multithreading() = runBlocking {
        val service: SomeService = FakeService()
        val repository: Repository = Repository.Base(service = service, dispatcher = Unconfined)
        val actual = repository.jokes(1000)
        val expected = (0.. 1000).map { it.toString() }
        assertEquals(expected, actual)
    }
}

private class FakeService : SomeService {

    private var count = 0

    override suspend fun load(): String {
        delay(1000)
        return (count++).toString()
    }
}