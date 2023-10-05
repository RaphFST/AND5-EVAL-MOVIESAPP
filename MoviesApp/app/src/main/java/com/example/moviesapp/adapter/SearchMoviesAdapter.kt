package com.example.moviesapp.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.databinding.SearchMoviesCellBinding
import com.example.moviesapp.network.dto.Movie
import com.example.moviesapp.network.utils.ApiLink
import com.squareup.picasso.Picasso
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


class SearchMovieAdapter(
    private val onItemClicked: (item: Movie) -> Unit
) : ListAdapter<Movie, SearchMovieAdapter.SearchMovieViewHolder>(DiffCallback) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieViewHolder {
        val viewHolder = SearchMovieViewHolder(
            SearchMoviesCellBinding.inflate(
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: SearchMovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SearchMovieViewHolder(
        private var binding: SearchMoviesCellBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(movie: Movie) {
            setupImage(ApiLink.imageMovieApi + movie.poster)
            binding.txtTitleMovieSearch.text = movie.title
            binding.txtReleaseMovieSearch.text = setDateTxt(movie.release)
            binding.txtVoteSearchMovie.text = movie.vote.toString()
        }

        private fun setupImage(url: String) {
            Picasso.get()
                .load(url)
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_placeholder)
                .fit()
                .centerCrop()
                .into(binding.imgMovieSearch)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        private fun setDateTxt(dateString: String): String? {
            val localDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            val dtf = DateTimeFormatter.ofPattern("MMM dd, uuuu", Locale.ENGLISH)
            return dtf.format(localDate)
        }

    }
}