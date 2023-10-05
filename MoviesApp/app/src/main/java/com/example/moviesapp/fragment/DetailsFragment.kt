package com.example.moviesapp.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentDetailsBinding
import com.example.moviesapp.network.utils.ApiLink
import com.example.moviesapp.viewModel.MovieViewModel
import com.squareup.picasso.Picasso
import com.example.moviesapp.viewModel.MovieViewModelFactory



class DetailsFragment : Fragment() {

    class DetailsFragment : Fragment() {
        private var binding: FragmentDetailsBinding? = null
        private val viewModel: MovieViewModel by activityViewModels {
            MovieViewModelFactory()
        }
        private var recyclerView: RecyclerView? = null
        private val args: DetailsFragmentArgs by navArgs()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

        }

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            binding = FragmentDetailsBinding.inflate(inflater, container, false)
            return binding?.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            setupContent()
            //setupRecyclerView()
        }

        override fun onDestroy() {
            binding = null
            super.onDestroy()
        }

        private fun setupContent() {
            binding?.imgBackMovieDetails?.let {
                setupImage(
                    ApiLink.imageMovieApi + args.movie.backdrop,
                    it
                )
            }
            binding?.imgPosterMovieDetails?.let {
                setupImage(
                    ApiLink.imageMovieApi + args.movie.poster,
                    it
                )
            }
            binding?.txtVoteMovieDetails?.text = args.movie.vote.toString()
            binding?.txtTitleMovieDetails?.text = args.movie.title
            binding?.txtDescriptionMovieDetails?.text = args.movie.overview
        }

        private fun setupImage(url: String, imageView: ImageView) {
            Picasso.get()
                .load(url)
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_placeholder)
                .fit()
                .centerCrop()
                .into(imageView)
        }

    }
}