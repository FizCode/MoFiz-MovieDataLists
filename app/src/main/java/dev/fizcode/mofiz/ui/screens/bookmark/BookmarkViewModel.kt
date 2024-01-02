package dev.fizcode.mofiz.ui.screens.bookmark

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.fizcode.mofiz.data.local.BookmarkEntity
import dev.fizcode.mofiz.repository.BookmarkRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
): ViewModel() {

    val shouldShowBookmarks: MutableStateFlow<List<BookmarkEntity>> = MutableStateFlow(emptyList())
    val shouldShowError: MutableState<Boolean> = mutableStateOf(false)
    val errorMessage: MutableState<String> = mutableStateOf("")

    fun onViewLoaded() {
        getBookmarks()
    }

    private fun getBookmarks() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = bookmarkRepository.getBookmarks()
            withContext(Dispatchers.Main) {
                result.let {
                    shouldShowBookmarks.value = it
                }
            }
        }
    }
}