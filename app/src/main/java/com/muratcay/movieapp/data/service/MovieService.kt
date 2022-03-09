package com.muratcay.movieapp.data.service

import com.muratcay.movieapp.core.model.DiscoverResponse
import com.muratcay.movieapp.core.model.FilmListResponse
import com.muratcay.movieapp.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("genre/movie/list")
    suspend fun getMovieGenres(
        @Query("api_key") api: String = API_KEY,
        @Query("language") language: String
    ): FilmListResponse

    @GET("discover/movie")
    suspend fun getMovies(
        @Query("api_key") api: String,
        @Query("language") language: String,
        @Query("sort_by") sort: String,
        @Query("page") page: Int,
        @Query("with_genres") withGenres: String,
        @Query("without_genres") withoutGenres: String,
        @Query("with_original_language") originalLanguage: String,
        @Query("without_original_language") notOriginalLanguage: String,
        @Query("release_date.lte") releaseDateLte: String,
        @Query("release_date.gte") releaseDateGte: String,
        @Query("vote_average.gte") voteAverageGte: String
    ): DiscoverResponse

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") api: String,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("query") query: String
    ): DiscoverResponse
}