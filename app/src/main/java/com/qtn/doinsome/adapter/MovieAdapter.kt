package com.qtn.doinsome.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.qtn.doinsome.R
import com.qtn.doinsome.data.remote.response.ResultsItem
import com.qtn.doinsome.databinding.MovieItemBinding

class MovieAdapter(private val list: List<ResultsItem>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class MovieViewHolder(private val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: ResultsItem) {
            binding.apply {
                tvTitleInEnglish.text = movie.title
                tvOriginalTitle.text = movie.originalTitle
                tvSynopsis.text = movie.overview
                ratingBar.rating = movie.voteAverage.toFloat()/2
                Glide.with(itemView.context)
                    .apply {
                        CenterCrop()
                    }
                    .load("https://image.tmdb.org/t/p/w500${movie.backdropPath}")
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ivMovie)

            }
        }

    }


}