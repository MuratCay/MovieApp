package com.muratcay.movieapp.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.muratcay.movieapp.core.model.Result
import com.muratcay.movieapp.core.paging.CategoryPagingSource
import com.muratcay.movieapp.core.paging.SearchPagingSource
import com.muratcay.movieapp.data.service.MovieService
import com.muratcay.movieapp.utils.Constants.API_KEY
import com.muratcay.movieapp.utils.Constants.POPULARITY_DESC
import com.muratcay.movieapp.utils.Constants.language
import javax.inject.Inject

class MovieRepository
@Inject constructor(private val movieService: MovieService) {

    suspend fun getMovieGenres(): Any {
        return movieService.getMovieGenres(language = language).genres
    }

    suspend fun getMovies(
        sort: String = POPULARITY_DESC,
        page: Int = 1,
        withGenres: String="",
        withoutGenres: String="",
        originalLanguage: String="",
        notOriginalLanguage: String="",
        releaseDateLte: String="",
        releaseDateGte: String="",
        voteAverageGte: String=""
    ): List<Result> {
        return movieService.getMovies(
            API_KEY,
            language,
            sort,
            page,
            withGenres,
            withoutGenres,
            originalLanguage,
            notOriginalLanguage,
            releaseDateLte,
            releaseDateGte,
            voteAverageGte
        ).results
    }

    suspend fun getPagingMovies(
        sort: String,
        withGenres: String,
        withoutGenres: String,
        originalLanguage: String,
        notOriginalLanguage: String,
        releaseDateLte: String,
        releaseDateGte: String,
        voteAverageGte: String
    ): LiveData<PagingData<Result>> {
        return Pager(config = PagingConfig(20, enablePlaceholders = false),
            pagingSourceFactory = {
                CategoryPagingSource(
                    API_KEY,
                    language,
                    sort,
                    withGenres,
                    withoutGenres,
                    originalLanguage,
                    notOriginalLanguage,
                    releaseDateLte,
                    releaseDateGte,
                    voteAverageGte,
                    movieService
                )
            }).liveData
    }

    suspend fun getSearchMovies(value: String): LiveData<PagingData<Result>> {
        return Pager(config = PagingConfig(20, enablePlaceholders = false),
            pagingSourceFactory = {
                SearchPagingSource(
                    API_KEY,
                    language,
                    value,
                    movieService
                )
            }).liveData
    }
}