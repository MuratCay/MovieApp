package com.muratcay.movieapp.ui.category.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.muratcay.movieapp.R
import com.muratcay.movieapp.core.base.BaseFragment
import com.muratcay.movieapp.databinding.FragmentCategoryBinding
import com.muratcay.movieapp.ui.category.viewmodel.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding>() {

    private val viewModel: CategoryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun getFragmentView() = R.layout.fragment_category

}