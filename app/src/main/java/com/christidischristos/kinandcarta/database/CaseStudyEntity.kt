package com.christidischristos.kinandcarta.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CaseStudyEntity(
    @PrimaryKey
    var id: Int,
    val client: String?,
    val teaser: String,
    val vertical: String,
    val is_enterprise: Boolean,
    val title: String,
    val hero_image: String,
    val app_store_url: String?,
    val sections: String
)
