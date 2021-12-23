package com.christidischristos.kinandcarta.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.christidischristos.kinandcarta.FakeData
import com.christidischristos.kinandcarta.MyApplication
import com.christidischristos.kinandcarta.core.StoryElement
import com.christidischristos.kinandcarta.database.CaseStudyDatabase
import com.christidischristos.kinandcarta.extensions.getOrAwaitValue
import com.christidischristos.kinandcarta.network.CaseStudyBodyElementDeserializer
import com.christidischristos.kinandcarta.network.CaseStudyNetworkService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@MediumTest
@RunWith(AndroidJUnit4::class)  // for robolectric
class CaseStudyRepositoryTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: CaseStudyRepository

    @Before
    fun setUp() = runTest {
        val applicationContext = ApplicationProvider.getApplicationContext<MyApplication>()
        // SOS: Note this is more of an integration test, for a unit-test we'd also mock the db!
        val database = Room.inMemoryDatabaseBuilder(
            applicationContext,
            CaseStudyDatabase::class.java
        ).allowMainThreadQueries().build()
        val mockService = mockk<CaseStudyNetworkService>()
        coEvery { mockService.getCaseStudies() } returns FakeData.caseStudiesResponse
        repository = CaseStudyRepository(database.dao, mockService, getGson())
    }

    @Test
    fun `when refreshing from net, liveData will get correct value`() = runTest {
        repository.fetchStudiesFromNetwork()
        val studies = repository.caseStudies.getOrAwaitValue()
        assertThat(studies.size, `is`(1))
    }

    @After
    fun tearDown() = runTest {
    }

    // SOS: Note this is more of an integration test, for a unit-test we'd also mock gson() here
    private fun getGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(
                StoryElement::class.java,
                CaseStudyBodyElementDeserializer()
            )
            .create()
    }
}
