package com.example.popularmoviesattempt2

data class Movies(
    val page: Int,
    val results: List<ResultXXX>,
    val total_pages: Int,
    val total_results: Int
)