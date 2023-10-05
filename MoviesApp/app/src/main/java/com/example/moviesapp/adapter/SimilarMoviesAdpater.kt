package com.example.moviesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.databinding.SimilarMovieCellBinding
import com.example.moviesapp.network.dto.Movie
import com.example.moviesapp.network.utils.ApiLink
import com.squareup.picasso.Picasso

class SimilarMoviesAdpater(
    private val onItemClicked: (item: Movie) -> Unit
) : ListAdapter<Movie, SimilarMoviesAdpater.SimilarMovieViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(
                oldItem: Movie,
                newItem: Movie
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Movie,
                newItem: Movie
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarMovieViewHolder {
        val viewHolder = SimilarMovieViewHolder(
            SimilarMovieCellBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: SimilarMovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SimilarMovieViewHolder(
        private var binding: SimilarMovieCellBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            setupImage(ApiLink.imageMovieApi + movie.poster)
            binding.txtTitleMovieSimilar.text = movie.title
        }

        private fun setupImage(url: String) {
            Picasso.get()
                .load(url)
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_placeholder)
                .fit()
                .centerCrop()
                .into(binding.imgMovieSimilar)
        }

    }
}