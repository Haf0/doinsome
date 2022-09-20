package com.qtn.doinsome.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qtn.doinsome.data.remote.RemoteRepository
import com.qtn.doinsome.data.remote.response.MovieResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: RemoteRepository) : ViewModel() {

    private val _list = MutableLiveData<MovieResponse>()
    val list: LiveData<MovieResponse> = _list

    private val _searchResult = MutableLiveData<MovieResponse>()
    val searchResult: LiveData<MovieResponse> = _searchResult


    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    init {
        getMovies()
    }
    private fun getMovies(){
        _loading.value = true
        viewModelScope.launch {
            repository.getMovie().let {
                Log.d("Cok", "getMovie: ${it.body()}")

                if (it.isSuccessful){
                    _loading.value = false
                    _list.postValue(it.body())
                }else Log.e("error", "getMovies: failed")
            }
        }
    }

    fun getMovieSearch(query: String) {
        _loading.value = true
        viewModelScope.launch {
            repository.getMovieSearch(query).let {
                Log.d("Cok", "getMovieSearch: ${it.body()}")
                if (it.isSuccessful){
                    _loading.value = false
                    _searchResult.postValue(it.body())
                }else Log.e("error", "getMovieSearch: failed")
            }
        }
    }


}