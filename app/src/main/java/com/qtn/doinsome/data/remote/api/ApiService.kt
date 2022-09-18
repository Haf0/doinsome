package com.qtn.doinsome.data.remote.api

import com.qtn.doinsome.data.remote.response.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/upcoming")
    suspend fun upcomingMovies(
        @Query("api_key") apiKey: String = "bb78e4cf3442e302d928f2c5edcdbee1"
    ): Response<MovieResponse>

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("api_key") apiKey: String = "bb78e4cf3442e302d928f2c5edcdbee1"
    ): Response<MovieResponse>





}