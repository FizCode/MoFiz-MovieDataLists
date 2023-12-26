package dev.fizcode.mofiz.ui.screens.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.fizcode.mofiz.data.api.MovieDetailsResponse
import dev.fizcode.mofiz.repository.MovieDetailsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository
): ViewModel() {

    val shouldShowDetails = MutableStateFlow(MovieDetailsResponse())
    val shouldShowError: MutableState<Boolean> = mutableStateOf(false)
    val errorMessage: MutableState<String> = mutableStateOf("")

    fun onViewLoaded(movieId: Int) {
        getMovieDetails(movieId = movieId)
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

}