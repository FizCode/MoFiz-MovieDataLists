package dev.fizcode.mofiz.repository

import dev.fizcode.mofiz.common.Constant
import dev.fizcode.mofiz.data.api.MovieListAPI
import dev.fizcode.mofiz.data.api.MovieListResponse
import retrofit2.Response
import javax.inject.Inject

class MovieListRepository @Inject constructor(
    private val api: MovieListAPI
) {
    private val key = Constant.API_KEY
    suspend fun getNowPlaying(): Response<MovieListResponse> {
        return api.getNowPlaying(key = key)
    }
    suspend fun getMostPopular(): Response<MovieListResponse> {
        return api.getMostPopular(key = key)
    }
    suspend fun getUpcoming(): Response<MovieListResponse> {
        return api.getUpcoming(key = key)
    }
}