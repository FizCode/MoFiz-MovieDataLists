package dev.fizcode.mofiz.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieListAPI {
    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") key: String
    ): Response<MovieListResponse>

    @GET("movie/popular")
    suspend fun getMostPopular(
        @Query("api_key") key: String
    ): Response<MovieListResponse>

    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("api_key") key: String
    ): Response<MovieListResponse>
}