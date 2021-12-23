package com.christidischristos.kinandcarta.repository

import com.christidischristos.kinandcarta.domain.CaseStudy
import java.io.IOException

class FetchStudiesUseCase(private val newRepository: INewRepository) {

    sealed class Result
    data class Success(val caseStudies: List<CaseStudy>) : Result()
    object NetworkError : Result()
    data class GenericError(val s: String) : Result()

    suspend fun execute(): Result {
        return try {
            Success(newRepository.fetchCaseStudies())
        } catch (e: IOException) {
            NetworkError
        } catch (e: Exception) {
            GenericError("bla")
        }
    }
}
