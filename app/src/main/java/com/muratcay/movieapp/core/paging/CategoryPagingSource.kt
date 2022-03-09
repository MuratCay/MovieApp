package com.muratcay.movieapp.core.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.muratcay.movieapp.core.model.Result
import com.muratcay.movieapp.data.service.MovieService
import com.muratcay.movieapp.utils.Constants.STARTING_PAGE_INDEX

class CategoryPagingSource(
    private val api: String,
    private val language: String,
    private val sort: String,
    private val withGenres: String,
    private val withoutGenres: String,
    private val originalLanguage: String,
    private val notOriginalLanguage: String,
    private val releaseDateLte: String,
    private val releaseDateGte: String,
    private val voteAverageGte: String,
    private val movieService: MovieService
) : PagingSource<Int, Result>() {

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val page = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = movieService.getMovies(
                api,
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
            )
            LoadResult.Page(
                response.results,
                if (page == STARTING_PAGE_INDEX) null else page.minus(1),
                if (response.results.isEmpty()) null else page.plus(1)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}