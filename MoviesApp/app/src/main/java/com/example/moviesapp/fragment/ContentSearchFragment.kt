package com.example.moviesapp.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.adapter.SearchMoviesAdapter
import com.example.moviesapp.databinding.FragmentContentSearchBinding
import com.example.moviesapp.network.dto.Movie
import com.example.moviesapp.viewModel.MovieViewModel
import com.example.moviesapp.viewModel.MovieViewModelFactory

class ContentSearchFragment(private val input: String) : Fragment() {
    private var binding: FragmentContentSearchBinding? = null
    private val viewModel: MovieViewModel by activityViewModels {
        MovieViewModelFactory()
    }
    private var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContentSearchBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private fun setupRecyclerView() {
        recyclerView = binding?.containerRecyclerSearchedMovie
        recyclerView?.layoutManager = LinearLayoutManager(activity)
        val itemAdapter = SearchMoviesAdapter { goToDetails(it) }
        recyclerView?.adapter = itemAdapter
        viewModel.getSearchMovie(input) { movies ->
            itemAdapter.submitList(movies)
        }
    }

    private fun goToDetails(movie: Movie) {
        val directions =
            SearchFragmentDirections.actionSearchFragmentToDetailsFragment(movie)
        findNavController().navigate(directions)
    }
}