package com.christidischristos.kinandcarta.repository

import com.christidischristos.kinandcarta.domain.CaseStudy

interface INewRepository {

    suspend fun fetchCaseStudies(): List<CaseStudy>
}
