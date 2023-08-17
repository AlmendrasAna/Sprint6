package com.example.sprint6


import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.sprint6.data.local.PhoneDB
import com.example.sprint6.data.local.PhoneDao
import com.example.sprint6.data.local.PhoneProductsEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class BreedRoomDatabaseTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var phoneDao: PhoneDao
    private lateinit var db: PhoneDB

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, PhoneDB::class.java).build()
        phoneDao = db.getDaoPhone()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertProducts_empty() = runBlocking {
        // Given
        val productsList = listOf<PhoneProductsEntity>()

        // When
        phoneDao.insertsListProductsEntity(productsList)

        // Then A
        val it = phoneDao.showListProductsEntity().getOrAwaitValue()
        Assert.assertNotEquals(it.size, null)//assertThat(it).isNotNull
        Assert.assertEquals(it.size, 0)//assertThat(it).isEmpty()

        // Then B
        phoneDao.showListProductsEntity().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isEmpty()
        }
    }

    @Test
    fun insertProducts_happyCase_1element() = runBlocking {
        // Given
        val productsList =
            listOf(PhoneProductsEntity(1, "sansung pirata", 30000, "Http//asdasd.jpg"))

        // When
        phoneDao.insertsListProductsEntity(productsList)

        // Then
        phoneDao.showListProductsEntity().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(1)
        }
    }

    @Test
    fun insertProducts_happyCase_3elements() = runBlocking {
        // Given
       val productsList = listOf(
            PhoneProductsEntity(1, "sansung pirata", 30000, "Http//asdasd.jpg"),
            PhoneProductsEntity(2, "huawei pirata", 40000, "Http//asdasd2.jpg"),
            PhoneProductsEntity(3, "xiomi pirata", 20000, "Http//asdasd4.jpg")
        )


        // When
        phoneDao.insertsListProductsEntity(productsList)

        // Then
        phoneDao.showListProductsEntity().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(3)
        }
    }
}


@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2, timeUnit: TimeUnit = TimeUnit.SECONDS, afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(value: T) {
            data = value
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)

    try {
        afterObserve.invoke()

        // Don't wait indefinitely if the LiveData is not set.
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }

    } finally {
        this.removeObserver(observer)
    }

    @Suppress("UNCHECKED_CAST") return data as T
}