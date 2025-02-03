package com.elmaddinasger.internintelligencemovieapp.services

import com.elmaddinasger.internintelligencemovieapp.models.Genres
import com.elmaddinasger.internintelligencemovieapp.models.OnlineMovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Header("Authorization") token: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Call<OnlineMovieList>

    @GET("movie/popular")
    fun getPopularMovies(
        @Header("Authorization") token: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Call<OnlineMovieList>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Header("Authorization") token: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Call<OnlineMovieList>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Header("Authorization") token: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Call<OnlineMovieList>

    @GET("genre/movie/list")
    fun getMovieGenreList(
        @Header("Authorization") token: String,
        @Query("language") language: String = "en",
    ): Call<Genres>
}