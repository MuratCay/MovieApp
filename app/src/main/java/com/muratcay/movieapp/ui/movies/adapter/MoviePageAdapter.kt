package com.muratcay.movieapp.ui.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muratcay.movieapp.core.model.Result
import com.muratcay.movieapp.databinding.ItemMovieImageBinding

class MoviePageAdapter : RecyclerView.Adapter<MoviePageAdapter.MoviePageViewHolder>() {

    private val aList = ArrayList<Result>()

    class MoviePageViewHolder(private val binding: ItemMovieImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: Result) {
            binding.resultModel = result
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePageViewHolder {
        return MoviePageViewHolder(
            ItemMovieImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviePageViewHolder, position: Int) {
        holder.bind(aList[position])
    }

    override fun getItemCount() = aList.size - 10

    fun updateList(list: List<Result>) {
        aList.clear()
        aList.addAll(list)
        notifyDataSetChanged()
    }
}