package com.example.moviesapp.network.services

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
    ): Response<Any>

    @Headers("Content-type: application/json")
    @GET("movie/{movie_id}/similar")
    suspend fun similarMovie(
        @Path("movie_id") id: String,
        @Query("api_key", encoded = true) apiKey: String
    ): Response<Any>

    @Headers("Content-type: application/json")
    @GET("trending/all/day")
    suspend fun trendMovie(
        @Query("api_key", encoded = true) apiKey: String
    ): Response<Any>

}

class MovieServiceImpl : MoviesServices(ApiLink.moviesApiLink) {
    suspend fun getSearchMovie(query: String): Response<Any> =
        getRetrofit().create(MovieService::class.java).searchMovie(query, "55530312075972a425f5fa13e21b218f")

    suspend fun getSimilarMovie(id: String): Response<Any> =
        getRetrofit().create(MovieService::class.java).similarMovie(id, "55530312075972a425f5fa13e21b218f")

    suspend fun getTrendMovie(): Response<Any> =
        getRetrofit().create(MovieService::class.java).trendMovie("55530312075972a425f5fa13e21b218f")
}