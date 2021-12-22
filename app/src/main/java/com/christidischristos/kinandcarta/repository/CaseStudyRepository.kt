package com.christidischristos.kinandcarta.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.christidischristos.kinandcarta.core.ImageElement
import com.christidischristos.kinandcarta.core.TextElement
import com.christidischristos.kinandcarta.database.CaseStudyDao
import com.christidischristos.kinandcarta.database.CaseStudyEntity
import com.christidischristos.kinandcarta.domain.CaseStudy
import com.christidischristos.kinandcarta.domain.StorySection
import com.christidischristos.kinandcarta.network.CaseStudiesResponse
import com.christidischristos.kinandcarta.network.CaseStudyNetworkService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CaseStudyRepository @Inject constructor(
    private val dao: CaseStudyDao,
    private val networkService: CaseStudyNetworkService,
    private val gson: Gson
) {

    val caseStudies: LiveData<List<CaseStudy>> = Transformations.map(dao.getCaseStudies()) {
        it.asDomainModel()
    }

    suspend fun fetchStudiesFromNetwork() {
        withContext(Dispatchers.IO) {
            val caseStudies = networkService.getCaseStudies().case_studies
            dao.insertAll(caseStudies.asDatabaseModel())
        }
    }

    private fun Collection<CaseStudyEntity>.asDomainModel(): List<CaseStudy> {
        val sectionListType = object : TypeToken<List<CaseStudiesResponse.StorySection>>() {}.type
        return this.map {
            CaseStudy(
                client = it.client,
                teaser = it.teaser,
                category = it.vertical,
                title = it.title,
                heroImageUrl = it.hero_image,
                storySections = gson.fromJson<List<CaseStudiesResponse.StorySection>>(
                    it.sections, sectionListType
                ).asDomainModel()
            )
        }
    }

    private fun Collection<CaseStudiesResponse.NetworkCaseStudy>.asDatabaseModel(): List<CaseStudyEntity> {
        return this.map {
            CaseStudyEntity(
                id = it.id,
                client = it.client,
                teaser = it.teaser,
                vertical = it.vertical,
                is_enterprise = it.is_enterprise,
                title = it.title,
                hero_image = it.hero_image,
                app_store_url = it.app_store_url,
                sections = gson.toJson(it.sections)
            )
        }
    }
}

private fun Collection<CaseStudiesResponse.StorySection>.asDomainModel(): List<StorySection> {
    return this.map {
        StorySection(
            title = it.title,
            bodyElements = it.body_elements.map { elmt ->
                when (elmt) {
                    is ImageElement -> elmt.imageUrl
                    is TextElement -> elmt.text
                }
            }
        )
    }
}

