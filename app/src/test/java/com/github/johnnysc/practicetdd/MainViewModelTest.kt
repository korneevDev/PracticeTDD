package com.github.johnnysc.practicetdd

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.github.johnnysc.practicetdd.seven.Communication
import com.github.johnnysc.practicetdd.seven.Good
import com.github.johnnysc.practicetdd.seven.GoodFilter
import com.github.johnnysc.practicetdd.seven.MainViewModel
import com.github.johnnysc.practicetdd.seven.OS
import com.github.johnnysc.practicetdd.seven.ProcessorType
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Test

class MainViewModelTest {

    @Test
    fun test_choose_2_different_filters() {
        val filters = mutableListOf<GoodFilter>(
            TestRamFilter(ram = 16),
            TestRamFilter(ram = 8),
            TestOsFilter(OS.MAC),
            TestOsFilter(OS.WINDOWS),
            TestOsFilter(OS.LINUX),
            TestPriceUnder(1600.0)
        )
        val products = mutableListOf<Good>(
            TestGood(8, OS.WINDOWS, 14.0, ProcessorType.INTEL, 1400.0),
            TestGood(16, OS.WINDOWS, 16.0, ProcessorType.INTEL, 1600.0),
            TestGood(8, OS.LINUX, 12.0, ProcessorType.AMD, 1400.0),
            TestGood(16, OS.LINUX, 16.0, ProcessorType.INTEL, 1600.0),
            TestGood(8, OS.MAC, 13.0, ProcessorType.M1, 2000.0),
            TestGood(16, OS.MAC, 13.0, ProcessorType.M1, 2500.0),
        )
        val communication = TestCommunication()
        val viewModel = MainViewModel(
            filters,
            products,
            communication,
            TestFiltersCommunication()
        )
        viewModel.change(TestRamFilter(ram = 16))
        var actual = communication.list
        var expected = listOf<Good>(
            TestGood(16, OS.WINDOWS, 16.0, ProcessorType.INTEL, 1600.0),
            TestGood(16, OS.LINUX, 16.0, ProcessorType.INTEL, 1600.0),
            TestGood(16, OS.MAC, 13.0, ProcessorType.M1, 2500.0),
        )
        assertEquals(expected, actual)

        viewModel.change(TestOsFilter(OS.MAC))
        actual = communication.list
        expected = listOf(
            TestGood(16, OS.MAC, 13.0, ProcessorType.M1, 2500.0),
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_add_and_remove_filters() {
        val filters = mutableListOf<GoodFilter>(
            TestRamFilter(ram = 16),
            TestRamFilter(ram = 8),
            TestOsFilter(OS.MAC),
            TestOsFilter(OS.WINDOWS),
            TestOsFilter(OS.LINUX),
            TestPriceUnder(1600.0)
        )
        val products = mutableListOf<Good>(
            TestGood(8, OS.WINDOWS, 14.0, ProcessorType.INTEL, 1400.0),
            TestGood(16, OS.WINDOWS, 16.0, ProcessorType.INTEL, 1600.0),
            TestGood(8, OS.LINUX, 12.0, ProcessorType.AMD, 1400.0),
            TestGood(16, OS.LINUX, 16.0, ProcessorType.INTEL, 1600.0),
            TestGood(8, OS.MAC, 13.0, ProcessorType.M1, 2000.0),
            TestGood(16, OS.MAC, 13.0, ProcessorType.M1, 2500.0),
        )
        val communication = TestCommunication()
        val viewModel = MainViewModel(
            filters,
            products,
            communication,
            TestFiltersCommunication()
        )
        viewModel.change(TestRamFilter(ram = 16))
        var actual = communication.list
        var expected = listOf<Good>(
            TestGood(16, OS.WINDOWS, 16.0, ProcessorType.INTEL, 1600.0),
            TestGood(16, OS.LINUX, 16.0, ProcessorType.INTEL, 1600.0),
            TestGood(16, OS.MAC, 13.0, ProcessorType.M1, 2500.0),
        )
        assertEquals(expected, actual)
        viewModel.change(TestRamFilter(ram = 16))

        actual = communication.list
        expected = listOf<Good>(
            TestGood(8, OS.WINDOWS, 14.0, ProcessorType.INTEL, 1400.0),
            TestGood(16, OS.WINDOWS, 16.0, ProcessorType.INTEL, 1600.0),
            TestGood(8, OS.LINUX, 12.0, ProcessorType.AMD, 1400.0),
            TestGood(16, OS.LINUX, 16.0, ProcessorType.INTEL, 1600.0),
            TestGood(8, OS.MAC, 13.0, ProcessorType.M1, 2000.0),
            TestGood(16, OS.MAC, 13.0, ProcessorType.M1, 2500.0),
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_add_2_then_remove_first() {
        val filters = mutableListOf<GoodFilter>(
            TestRamFilter(ram = 16),
            TestRamFilter(ram = 8),
            TestOsFilter(OS.MAC),
            TestOsFilter(OS.WINDOWS),
            TestOsFilter(OS.LINUX),
            TestPriceUnder(1600.0)
        )
        val products = mutableListOf<Good>(
            TestGood(8, OS.WINDOWS, 14.0, ProcessorType.INTEL, 1400.0),
            TestGood(16, OS.WINDOWS, 16.0, ProcessorType.INTEL, 1600.0),
            TestGood(8, OS.LINUX, 12.0, ProcessorType.AMD, 1400.0),
            TestGood(16, OS.LINUX, 16.0, ProcessorType.INTEL, 1600.0),
            TestGood(8, OS.MAC, 13.0, ProcessorType.M1, 2000.0),
            TestGood(16, OS.MAC, 13.0, ProcessorType.M1, 2500.0),
        )
        val communication = TestCommunication()
        val viewModel = MainViewModel(
            filters,
            products,
            communication,
            TestFiltersCommunication()
        )
        viewModel.change(TestRamFilter(ram = 16))
        var actual = communication.list
        var expected = listOf<Good>(
            TestGood(16, OS.WINDOWS, 16.0, ProcessorType.INTEL, 1600.0),
            TestGood(16, OS.LINUX, 16.0, ProcessorType.INTEL, 1600.0),
            TestGood(16, OS.MAC, 13.0, ProcessorType.M1, 2500.0),
        )
        assertEquals(expected, actual)

        viewModel.change(TestOsFilter(OS.MAC))
        actual = communication.list
        expected = listOf(
            TestGood(16, OS.MAC, 13.0, ProcessorType.M1, 2500.0),
        )
        assertEquals(expected, actual)

        viewModel.change(TestRamFilter(ram = 16))
        actual = communication.list
        expected = listOf(
            TestGood(8, OS.MAC, 13.0, ProcessorType.M1, 2000.0),
            TestGood(16, OS.MAC, 13.0, ProcessorType.M1, 2500.0),
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_success() {
        val repository = FakeRepositoryRx(true)
        val communication = FakeCommunicationRx()
        val mainViewModel = MainViewModelRx(
            repository = repository,
            communication = communication,
            schedulersList = TestSchedulersList()
        )
        mainViewModel.fetch()
        assertEquals("success", communication.value)
    }

    @Test
    fun test_error() {
        val repository = FakeRepositoryRx(false)
        val communication = FakeCommunicationRx()
        val mainViewModel = MainViewModelRx(
            repository,
            communication,
            TestSchedulersList()
        )
        mainViewModel.fetch()
        assertEquals("network problem", communication.value)
    }

    @Test
    fun test_single_live_event() {
        val myLiveData: MyObservable<String> = MyObservable.SingleLiveEvent()
        val observer = FakeObserver()
        val viewModel = MainViewModel(liveData = myLiveData)
        viewModel.updateObserver(observer = observer)
        viewModel.go()
        assertEquals("1", observer.list[0])
        assertEquals(1, observer.list.size)
        viewModel.go()
        assertEquals("2", observer.list[1])
        assertEquals(2, observer.list.size)
        viewModel.updateObserver(MyObserver.Empty())
        viewModel.go()
        assertEquals(2, observer.list.size)
        viewModel.updateObserver(observer)
        viewModel.notifyChanges()
        assertEquals("3", observer.list[2])
        assertEquals(3, observer.list.size)
        viewModel.notifyChanges()
        assertEquals(3, observer.list.size)
    }

    @Test
    fun test_base() {
        val myLiveData: MyObservable<String> = MyObservable.Base()
        val observer = FakeObserver()
        val viewModel = MainViewModel(liveData = myLiveData)
        viewModel.updateObserver(observer = observer)
        viewModel.go()
        assertEquals("1", observer.list[0])
        assertEquals(1, observer.list.size)
        viewModel.go()
        assertEquals("2", observer.list[1])
        assertEquals(2, observer.list.size)
        viewModel.updateObserver(MyObserver.Empty())
        viewModel.go()
        assertEquals(2, observer.list.size)
        viewModel.updateObserver(observer)
        viewModel.notifyChanges()
        assertEquals("3", observer.list[2])
        assertEquals(3, observer.list.size)
        viewModel.notifyChanges()
        assertEquals("3", observer.list[3])
        assertEquals(4, observer.list.size)
    }
}

private class TestCommunication : Communication<List<Good>> {

    val list = mutableListOf<Good>()

    override fun observe(owner: LifecycleOwner, observer: Observer<List<Good>>) = Unit

    override fun map(source: List<Good>) {
        list.clear()
        list.addAll(source)
    }
}

private class TestFiltersCommunication : Communication<List<GoodFilter>> {
    val list = mutableListOf<GoodFilter>()

    override fun map(source: List<GoodFilter>) {
        list.clear()
        list.addAll(source)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<List<GoodFilter>>) = Unit
}

private data class TestOsFilter(private val os: OS) : GoodFilter.Abstract() {

    override fun map(
        ram: Int,
        os: OS,
        displaySize: Double,
        processor: ProcessorType,
        price: Double
    ): Boolean = this.os == os
}

private data class TestRamFilter(private val ram: Int) : GoodFilter.Abstract() {

    override fun map(
        ram: Int,
        os: OS,
        displaySize: Double,
        processor: ProcessorType,
        price: Double
    ): Boolean = this.ram == ram
}

private data class TestPriceUnder(private val maxPrice: Double) : GoodFilter.Abstract() {

    override fun map(
        ram: Int,
        os: OS,
        displaySize: Double,
        processor: ProcessorType,
        price: Double
    ): Boolean = this.maxPrice >= price
}

private data class TestGood(
    private val ram: Int = 16,
    private val os: OS = OS.MAC,
    private val displaySize: Double = 13.0,
    private val processor: ProcessorType = ProcessorType.M1,
    private val price: Double = 2000.0
) : Good {

    override fun <T> map(mapper: Good.Mapper<T>): T =
        mapper.map(ram, os, displaySize, processor, price)

    override fun check(filter: GoodFilter): Boolean =
        filter.map(ram, os, displaySize, processor, price)

}

private class FakeRepositoryRx(private val success: Boolean) : RepositoryRx {
    override fun fetch(): Single<String> = if (success)
        Single.just("success")
    else
        Single.error(IllegalStateException("network problem"))
}

private class FakeCommunicationRx : CommunicationRx {
    var value: String = ""

    override fun observe(owner: LifecycleOwner, observer: Observer<String>) = Unit

    override fun map(data: String) {
        value = data
    }
}

private class TestSchedulersList : SchedulersList {
    private val sheduler = Schedulers.trampoline()
    override fun io(): Scheduler = sheduler
    override fun ui(): Scheduler = sheduler
}
private class FakeObserver : MyObserver<String> {
    val list = ArrayList<String>()
    override fun update(value: String) {
        list.add(value)
    }
}
