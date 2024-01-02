package dev.fizcode.mofiz.ui.screens.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.fizcode.mofiz.data.api.MovieDetailsResponse
import dev.fizcode.mofiz.data.local.BookmarkEntity
import dev.fizcode.mofiz.repository.BookmarkRepository
import dev.fizcode.mofiz.repository.MovieDetailsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository,
    private val bookmarkRepository: BookmarkRepository
): ViewModel() {

    val shouldShowDetails = MutableStateFlow(MovieDetailsResponse())
    val isBookmarked: MutableState<Boolean> = mutableStateOf(false)
    val shouldShowSuccess: MutableState<Boolean> = mutableStateOf(false)
    val successMessage: MutableState<String> = mutableStateOf("")
    val shouldShowError: MutableState<Boolean> = mutableStateOf(false)
    val errorMessage: MutableState<String> = mutableStateOf("")

    fun onViewLoaded(movieId: Int) {
        getMovieDetails(movieId = movieId)
        getIsBookmarked(movieId = movieId)
    }

    private fun getMovieDetails(movieId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = movieDetailsRepository.getMovieDetails(movieId = movieId)
            withContext(Dispatchers.Main) {
                if (result.isSuccessful) {
                    shouldShowDetails.value = result.body()!!
                } else {
                    shouldShowError.value = true
                    errorMessage.value = result.message().orEmpty()
                }
            }
        }
    }

    private fun getIsBookmarked(movieId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val bookmarked = bookmarkRepository.isBookmarked(movieId = movieId)
            withContext(Dispatchers.Main) {
                isBookmarked.value = bookmarked
            }
        }
    }

    fun bookmarking() {
        CoroutineScope(Dispatchers.IO).launch {
            println("Bookmarking")
            val movieDetails = shouldShowDetails.value
            val movieData = movieDetails.let {
                BookmarkEntity(
                    movieId = it.id.hashCode(),
                    title = it.title.toString(),
                    posterPath = it.posterPath.toString(),
                    voteAverage = it.voteAverage ?: 0.0
                )
            }

            val result = bookmarkRepository.insertBookmark(movieData)
            withContext(Dispatchers.Main) {
                if (result != 0L) {
                    shouldShowSuccess.value = true
                    successMessage.value = "Success adding to Bookmark"
                    isBookmarked.value = true
                } else {
                    shouldShowError.value = true
                    errorMessage.value = "Bookmark Failed."
                }
            }
        }
    }

    fun deleteBookmark() {
        CoroutineScope(Dispatchers.IO).launch {
            println("Deleting")
            val id = shouldShowDetails.value.id
            id?.let { bookmarkRepository.deleteBookmark(movieId = it) }
            withContext(Dispatchers.Main) {
                shouldShowSuccess.value = true
                successMessage.value = "Success remove from Bookmark"
                isBookmarked.value = false
            }
        }
    }
}