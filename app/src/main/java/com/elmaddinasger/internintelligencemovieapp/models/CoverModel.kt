package com.elmaddinasger.internintelligencemovieapp.models

data class CoverModel(
    val id: Long,
    val coverName: String,
    val movieList: MutableList<LocalMovieModel>
)
