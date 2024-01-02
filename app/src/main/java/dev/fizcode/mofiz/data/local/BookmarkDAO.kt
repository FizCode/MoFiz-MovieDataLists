package dev.fizcode.mofiz.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookmarkDAO {
    @Query("SELECT * FROM bookmark")
    fun getBookmarks(): List<BookmarkEntity>

    @Query("SELECT * FROM bookmark WHERE movie_id = :movieId")
    fun getBookmarkByMovieId(movieId: Int): BookmarkEntity

    @Query("SELECT * FROM bookmark WHERE movie_id = :movieId ")
    fun isBookmarked(movieId: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(movieListEntity: BookmarkEntity): Long

    @Query("DELETE FROM bookmark WHERE movie_id = :movieId")
    suspend fun deleteBookmark(movieId: Int): Int

}