package com.example.moviesapp.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.FragmentDetailsBinding
import com.example.moviesapp.viewModel.MovieViewModel
import com.example.moviesapp.viewModel.MovieViewModelFactory

class DetailsFragment : Fragment() {
    private var binding: FragmentDetailsBinding? = null
    private val viewModel: MovieViewModel by activityViewModels {
        MovieViewModelFactory()
    }
    private var recyclerView: RecyclerView? = null
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
        //setupRecyclerView()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

}