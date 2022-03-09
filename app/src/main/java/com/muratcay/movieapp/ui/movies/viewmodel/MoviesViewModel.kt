package com.muratcay.movieapp.ui.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muratcay.movieapp.core.model.Result
import com.muratcay.movieapp.data.repository.MovieRepository
import com.muratcay.movieapp.utils.Constants.POPULARITY_DESC
import com.muratcay.movieapp.utils.Status
import com.muratcay.movieapp.utils.categories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val mutableAllMovies: MutableLiveData<List<Result>> = MutableLiveData()
    val allMovies: LiveData<List<Result>>
        get() = mutableAllMovies

    private val mutableStatus: MutableLiveData<Status> = MutableLiveData()
    val status: LiveData<Status>
        get() = mutableStatus

    private val mutableCategories: MutableLiveData<List<Result>> = MutableLiveData()
    val categoriesLiveData: LiveData<List<Result>>
        get() = mutableCategories

    init {
        getAllMovies()
    }

    private fun getAllMovies() {
        viewModelScope.launch {
            mutableStatus.value = Status.LOADING
            try {
                mutableAllMovies.postValue(repository.getMovies())
                mutableStatus.value = Status.SUCCESS
            } catch (e: Exception) {
                mutableStatus.value = Status.ERROR
            }
        }
    }

    fun getCategoryResult() {
        val newCategoryList = arrayListOf<Result>()
        viewModelScope.launch {
            categories.forEach {
                val result = repository.getMovies(
                    POPULARITY_DESC,
                    1,
                    "",
                    "",
                    it.originalLanguage,
                    it.notOriginalLanguage,
                    it.releaseDateLte,
                    it.releaseDateGte,
                    it.voteAverageGte
                )
                // title eklencek
                newCategoryList.add(result as Result)
            }
            mutableCategories.postValue(newCategoryList)
        }
    }

}