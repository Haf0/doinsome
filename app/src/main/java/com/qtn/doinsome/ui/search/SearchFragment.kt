package com.qtn.doinsome.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.qtn.doinsome.R
import com.qtn.doinsome.adapter.MovieAdapter
import com.qtn.doinsome.data.remote.response.ResultsItem
import com.qtn.doinsome.databinding.FragmentSearchBinding
import com.qtn.doinsome.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val binding by viewBinding(FragmentSearchBinding::bind)
    private val viewModel by viewModels<MovieViewModel>()
    private lateinit var adapter: MovieAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        doSearch()
        viewModel.searchResult.observe(viewLifecycleOwner) {
            setUpAdapter(it.results)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    private fun doSearch(){
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                getMovie(query.toString())
                loadingState()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    private fun setUpAdapter(list:List<ResultsItem>){
        adapter = MovieAdapter(list)
        binding.rvSearch.layoutManager = LinearLayoutManager(activity)
        binding.rvSearch.adapter = adapter
    }

    private fun getMovie(query:String){
        with(viewModel) {
            getMovieSearch(query)

        }
    }
    private fun loadingState(){
        viewModel.loading.observe(viewLifecycleOwner){
            binding.rvSearch.isVisible = !it
            binding.progressIndicator.isVisible = it
        }
    }

}