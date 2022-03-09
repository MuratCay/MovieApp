package com.muratcay.movieapp.ui.movies.fragment

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.muratcay.movieapp.R
import com.muratcay.movieapp.core.base.BaseFragment
import com.muratcay.movieapp.databinding.FragmentMoviesBinding
import com.muratcay.movieapp.ui.movies.adapter.MovieCategoryAdapter
import com.muratcay.movieapp.ui.movies.adapter.MoviePageAdapter
import com.muratcay.movieapp.ui.movies.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding>() {

    private val viewModel: MoviesViewModel by viewModels()
    private val moviePageAdapter: MoviePageAdapter by lazy { MoviePageAdapter() }
    private val movieCategoryAdapter: MovieCategoryAdapter by lazy { MovieCategoryAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        configureToolbar()
        getAllMovies()
        getSwipeRefreshLayout()
        getCategories()
    }

    private fun configureToolbar() {
        (activity as AppCompatActivity).apply {
            setSupportActionBar(binding.moviesToolbar)
            supportActionBar?.title = ""
        }
    }

    private fun getCategories() {
        binding.moviesRecyclerView.adapter = movieCategoryAdapter

        viewModel.categoriesLiveData.observe(viewLifecycleOwner) {
            binding.swipeRefreshLayout.isRefreshing = false
            movieCategoryAdapter.list = it
        }
    }

    private fun getSwipeRefreshLayout() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getCategoryResult()
        }
    }

    private fun getAllMovies() {
        viewModel.allMovies.observe(viewLifecycleOwner) { resultList ->
            resultList.forEach {
                moviePageAdapter.updateList(resultList)
                Log.e("Data", it.posterPath)
            }
        }
        binding.viewPager.apply {
            adapter = moviePageAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }
        moviePageAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                binding.fragmentMoviesIndicator.setViewPager(binding.viewPager)
                super.onChanged()
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.search) {
            findNavController().navigate(MoviesFragmentDirections.actionGlobalSearchFragment())
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getFragmentView() = R.layout.fragment_movies
}