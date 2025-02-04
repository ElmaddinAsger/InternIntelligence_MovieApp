package com.elmaddinasger.internintelligencemovieapp.dto

import com.elmaddinasger.internintelligencemovieapp.models.LocalMovieModel
import com.elmaddinasger.internintelligencemovieapp.models.Result

fun Result.toLocalMovieModelDTO(): LocalMovieModel {
    return LocalMovieModel(
        adult = adult,
        backdropPath= "https://image.tmdb.org/t/p/w500$backdrop_path",
        genreIds= genre_ids,
        id= id,
        originalLanguage= original_language,
        originalTitle= original_title,
        overview= overview,
        popularity= popularity,
        posterPath= "https://image.tmdb.org/t/p/w500$poster_path",
        releaseDate= release_date,
        title= title,
        video= video,
        voteAverage= vote_average,
        voteCount= vote_count
    )
}