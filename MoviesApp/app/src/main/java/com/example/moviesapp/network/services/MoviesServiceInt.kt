package com.example.moviesapp.network.services

import com.example.moviesapp.network.dto.Results
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import com.example.moviesapp.network.utils.ApiLink


interface MovieService {
    @Headers("Content-type: application/json")
    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query", encoded = true) query: String,
        @Query("api_key", encoded = true) apiKey: String
    ): Response<Results>

    @Headers("Content-type: application/json")
    @GET("movie/{movie_id}/similar")
    suspend fun similarMovie(
        @Path("movie_id") id: String,
        @Query("api_key", encoded = true) apiKey: String
    ): Response<Results>

    @Headers("Content-type: application/json")
    @GET("trending/all/day")
    suspend fun trendMovie(
        @Query("api_key", encoded = true) apiKey: String
    ): Response<Results>

}

class MovieServiceImpl : MoviesServices(ApiLink.moviesApiLink) {
    suspend fun getSearchMovie(query: String): Response<Results> =
        getRetrofit().create(MovieService::class.java).searchMovie(query, ApiLink.apiKey)

    suspend fun getSimilarMovie(id: String): Response<Results> =
        getRetrofit().create(MovieService::class.java).similarMovie(id, ApiLink.apiKey)

    suspend fun getTrendMovie(): Response<Results> =
        getRetrofit().create(MovieService::class.java).trendMovie(ApiLink.apiKey)
}