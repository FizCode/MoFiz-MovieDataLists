package dev.fizcode.mofiz.repository

import dev.fizcode.mofiz.data.local.BookmarkDAO
import dev.fizcode.mofiz.data.local.BookmarkEntity
import javax.inject.Inject

class BookmarkRepository @Inject constructor(
    private val dao: BookmarkDAO
) {

    fun getBookmarks(): List<BookmarkEntity> {
        return dao.getBookmarks()
    }

    fun getBookmarkByMovieId(movieId: Int): BookmarkEntity {
        return dao.getBookmarkByMovieId(movieId = movieId)
    }

    fun isBookmarked(movieId: Int): Boolean {
        return dao.isBookmarked(movieId = movieId)
    }

    suspend fun insertBookmark(bookmarkEntity: BookmarkEntity): Long {
        val movieId = bookmarkEntity.movieId
        val isBookmarked = movieId?.let { dao.isBookmarked(it) }
        return if (isBookmarked == false) dao.insertBookmark(bookmarkEntity)
        else 0
    }

    suspend fun deleteBookmark(movieId: Int): Int {
        return dao.deleteBookmark(movieId = movieId)
    }
}