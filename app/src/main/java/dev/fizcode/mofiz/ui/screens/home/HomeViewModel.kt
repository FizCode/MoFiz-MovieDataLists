package dev.fizcode.mofiz.ui.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.fizcode.mofiz.data.api.MovieListResponse
import dev.fizcode.mofiz.repository.MovieListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieListRepository: MovieListRepository
): ViewModel() {

    val shouldShowNowPlaying = MutableStateFlow(MovieListResponse())
    val shouldShowError: MutableState<Boolean> = mutableStateOf(false)
    val errorMessage: MutableState<String> = mutableStateOf("")

    fun onViewLoaded() {
        getNowPlaying()
    }

    private fun getNowPlaying() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = movieListRepository.getNowPlaying()
            withContext(Dispatchers.Main) {
                if (result.isSuccessful) {
                    shouldShowNowPlaying.value = result.body()!!
                } else {
                    shouldShowError.value = true
                    errorMessage.value = result.message().orEmpty()
                }
            }
        }
    }
}