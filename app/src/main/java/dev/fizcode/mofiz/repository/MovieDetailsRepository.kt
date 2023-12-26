package dev.fizcode.mofiz.repository

import dev.fizcode.mofiz.common.Constant
import dev.fizcode.mofiz.data.api.MovieDetailsAPI
import dev.fizcode.mofiz.data.api.MovieDetailsResponse
import retrofit2.Response
import javax.inject.Inject

class MovieDetailsRepository @Inject constructor(
    private val api: MovieDetailsAPI
) {
    private val key = Constant.API_KEY

    suspend fun getMovieDetails(movieId: Int): Response<MovieDetailsResponse> {
        return api.getMovieDetails(key = key, movieId = movieId)
    }
}