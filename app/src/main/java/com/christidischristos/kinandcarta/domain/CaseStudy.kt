package com.christidischristos.kinandcarta.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CaseStudy(
    val client: String?,
    val teaser: String?,
    val category: String,
    val title: String,
    val heroImageUrl: String,
    val storySections: List<StorySection>
) : Parcelable

@Parcelize
data class StorySection(
    val title: String?,
    val bodyElements: List<String>
) : Parcelable
