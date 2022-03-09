package com.muratcay.movieapp.core.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.muratcay.movieapp.core.model.Result
import com.muratcay.movieapp.data.service.MovieService
import com.muratcay.movieapp.utils.Constants.STARTING_PAGE_INDEX

class SearchPagingSource(
    private val api: String,
    private val language: String,
    private val query: String,
    private val movieService: MovieService
) : PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = movieService.searchMovies(
                api,
                language,
                position,
                query
            )
            LoadResult.Page(
                response.results,
                if (position == STARTING_PAGE_INDEX) null else position.minus(1),
                if (response.results.isEmpty()) null else position.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }
}