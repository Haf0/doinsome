package com.qtn.doinsome.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.qtn.doinsome.adapter.MovieAdapter
import com.qtn.doinsome.data.remote.response.ResultsItem
import com.qtn.doinsome.databinding.FragmentHomeBinding
import com.qtn.doinsome.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val viewModel by viewModels<MovieViewModel>()
    private lateinit var adapter: MovieAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentHomeBinding.inflate(inflater, container, false).root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.list.observe(viewLifecycleOwner) {
            setUpAdapter(it.results)
            Log.d("list", "onViewCreated: $it")
        }
        loadingState()
    }

    private fun setUpAdapter(list:List<ResultsItem>){
        adapter = MovieAdapter(list)
        binding.rvUpcomingMovies.layoutManager = LinearLayoutManager(activity)
        binding.rvUpcomingMovies.adapter = adapter

    }

    private fun loadingState(){
        viewModel.loading.observe(viewLifecycleOwner){
            binding.rvUpcomingMovies.isVisible = !it
            binding.progressIndicator.isVisible = it
        }
    }

}