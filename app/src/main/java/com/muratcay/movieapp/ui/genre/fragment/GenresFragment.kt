package com.muratcay.movieapp.ui.genre.fragment

import androidx.fragment.app.viewModels
import com.muratcay.movieapp.R
import com.muratcay.movieapp.core.base.BaseFragment
import com.muratcay.movieapp.databinding.FragmentGenresBinding
import com.muratcay.movieapp.ui.genre.viewmodel.GenresViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenresFragment : BaseFragment<FragmentGenresBinding>() {

    private val viewModel: GenresViewModel by viewModels()

    override fun getFragmentView() = R.layout.fragment_genres
}