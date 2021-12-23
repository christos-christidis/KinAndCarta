package com.christidischristos.kinandcarta

import com.christidischristos.kinandcarta.core.ImageElement
import com.christidischristos.kinandcarta.core.TextElement
import com.christidischristos.kinandcarta.domain.CaseStudy
import com.christidischristos.kinandcarta.domain.StorySection
import com.christidischristos.kinandcarta.network.CaseStudiesResponse
import com.github.javafaker.Faker

object FakeData {

    private val faker = Faker()

    val caseStudiesResponse = CaseStudiesResponse(
        case_studies = listOf(
            CaseStudiesResponse.NetworkCaseStudy(
                id = 1,
                client = faker.company().name(),
                teaser = faker.lorem().sentence(),
                vertical = faker.company().industry(),
                is_enterprise = faker.bool().bool(),
                title = faker.lorem().sentence(),
                hero_image = faker.internet().url(),
                app_store_url = faker.internet().url(),
                sections = listOf(
                    CaseStudiesResponse.StorySection(
                        title = faker.lorem().sentence(),
                        body_elements = listOf(
                            TextElement(faker.lorem().sentence()),
                            ImageElement(faker.internet().url())
                        )
                    )
                )
            )
        )
    )

    val caseStudies = listOf(
        CaseStudy(
            client = faker.company().name(),
            teaser = faker.lorem().sentence(),
            category = faker.company().industry(),
            title = faker.lorem().sentence(),
            heroImageUrl = faker.internet().url(),
            storySections = listOf(
                StorySection(
                    title = faker.lorem().sentence(),
                    bodyElements = listOf(
                        faker.lorem().sentence(),
                        faker.internet().url()
                    )
                )
            )
        )
    )
}
