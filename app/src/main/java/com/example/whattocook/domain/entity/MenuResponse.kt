package com.example.whattocook.domain.entity

data class MenuResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)