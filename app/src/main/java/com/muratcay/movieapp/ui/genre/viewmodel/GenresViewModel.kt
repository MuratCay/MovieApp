package com.muratcay.movieapp.ui.genre.viewmodel

import androidx.lifecycle.ViewModel
import com.muratcay.movieapp.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GenresViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {
}