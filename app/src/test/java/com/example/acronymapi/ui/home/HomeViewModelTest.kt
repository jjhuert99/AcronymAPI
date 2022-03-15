package com.example.acronymapi.ui.home

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.acronymapi.common.ServiceResult
import com.example.acronymapi.network.AcronymRepoImpl
import com.example.acronymapi.network.AcronymResults
import com.example.acronymapi.network.DataModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModelTest: HomeViewModel
    private val testApp = mockk<Application>(relaxed = true)
    private val testRepo = mockk<AcronymRepoImpl>(relaxed = true)
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp(){
        Dispatchers.setMain(TestCoroutineDispatcher())
        viewModelTest = HomeViewModel(
            app = testApp,
            dispatchers = testDispatcher,
            retroObject = testRepo
        )
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun `getAcronyms should return a list of definitions for a given Acronym`()
    = runBlockingTest {
        coEvery{testRepo.getResults(any())} returns createSuccessfulCall()

        viewModelTest.getAcronymResults()

        assertEquals(
            2, viewModelTest.accResults.value?.size
        )

        assertEquals(
            createSuccessfulCall().data?.get(0)?.lfs?.get(0)?.lf, viewModelTest.accResults.value?.get(0)?.lf
        )
    }

    private fun createSuccessfulCall(): ServiceResult.Success<List<DataModel>?> {
        return ServiceResult.Success(
            listOf(
                mockk<DataModel>(){
                    every { lfs } returns listOf(
                        mockk<AcronymResults>(){
                            every { lf } returns "heavy meromyosin"
                            every {freq} returns 25
                            every { since } returns 1995
                        },
                        mockk<AcronymResults>(){
                            every { lf } returns "heavy meromyosin"
                            every {freq} returns 25
                            every { since } returns 1995
                        }
                    )
                    every { sf } returns "HMM"
                }
            )
        )
    }
}
