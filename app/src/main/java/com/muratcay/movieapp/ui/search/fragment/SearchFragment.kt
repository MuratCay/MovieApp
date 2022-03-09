package com.muratcay.movieapp.ui.search.fragment

import androidx.fragment.app.viewModels
import com.muratcay.movieapp.R
import com.muratcay.movieapp.core.base.BaseFragment
import com.muratcay.movieapp.databinding.FragmentSearchBinding
import com.muratcay.movieapp.ui.search.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private val viewModel: SearchViewModel by viewModels()



    override fun getFragmentView() = R.layout.fragment_search
}