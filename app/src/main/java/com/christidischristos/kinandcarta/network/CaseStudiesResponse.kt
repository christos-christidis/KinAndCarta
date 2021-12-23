package com.christidischristos.kinandcarta.network

import com.christidischristos.kinandcarta.core.StoryElement

data class CaseStudiesResponse(val case_studies: List<NetworkCaseStudy>) {

    data class NetworkCaseStudy(
        val id: Int,
        val client: String?,
        val teaser: String,
        val vertical: String,
        val is_enterprise: Boolean,
        val title: String,
        val hero_image: String,
        val app_store_url: String?,
        val sections: List<StorySection>
    )

    data class StorySection(
        val title: String?,
        val body_elements: List<StoryElement>
    )
}
