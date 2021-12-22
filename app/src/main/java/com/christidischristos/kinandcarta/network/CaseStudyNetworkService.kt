package com.christidischristos.kinandcarta.network

import retrofit2.http.GET

interface CaseStudyNetworkService {

    @GET("theappbusiness/engineering-challenge/main/endpoints//v1/caseStudies.json")
    suspend fun getCaseStudies(): CaseStudiesResponse
}
