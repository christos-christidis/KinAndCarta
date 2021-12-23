package com.christidischristos.kinandcarta.repository

import androidx.test.filters.SmallTest
import com.christidischristos.kinandcarta.FakeData
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.io.IOException

@SmallTest
class FetchStudiesUseCaseTest {

    private val mockRepository = mockk<INewRepository>()
    private val useCase = FetchStudiesUseCase(mockRepository)

    @Test
    fun `when repo throws io, then return NetworkError`() {
        coEvery { mockRepository.fetchCaseStudies() } throws IOException()
        val result = runBlocking { useCase.execute() }
        assertEquals("error msg", result, FetchStudiesUseCase.NetworkError)
    }

    @Test
    fun `when repo throws exception other than io, then return GenericError`() {
        coEvery { mockRepository.fetchCaseStudies() } throws IllegalStateException()
        val result = runBlocking { useCase.execute() }
        assertEquals("msg", result, FetchStudiesUseCase.GenericError("bla"))
    }

    @Test
    fun `when repo returns list of case studies, then return list`() {
        coEvery { mockRepository.fetchCaseStudies() } returns FakeData.caseStudies
        val result = runBlocking { useCase.execute() }
        assertEquals("msg", result, FetchStudiesUseCase.Success(FakeData.caseStudies))
    }
}
