package com.elmaddinasger.internintelligencemovieapp.models

data class OnlineMovieList(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)