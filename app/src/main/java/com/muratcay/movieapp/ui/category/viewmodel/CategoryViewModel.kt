package com.muratcay.movieapp.ui.category.viewmodel

import androidx.lifecycle.ViewModel
import com.muratcay.movieapp.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

}