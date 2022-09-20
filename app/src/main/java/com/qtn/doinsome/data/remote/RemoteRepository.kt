package com.qtn.doinsome.data.remote

import com.qtn.doinsome.data.remote.api.ApiService
import com.qtn.doinsome.data.remote.response.MovieResponse
import retrofit2.Response
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getMovie():Response<MovieResponse> = apiService.upcomingMovies()
    suspend fun getMovieSearch(query: String):Response<MovieResponse> = apiService.searchMovies(query)
}