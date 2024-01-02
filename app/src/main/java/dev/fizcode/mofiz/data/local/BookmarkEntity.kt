package dev.fizcode.mofiz.data.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "bookmark")
@Parcelize
data class BookmarkEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "movie_id") var movieId: Int? = null,
    @ColumnInfo(name = "poster_path") var posterPath: String? = null,
    @ColumnInfo(name = "title") var title: String? = null,
    @ColumnInfo(name = "vote_average") var voteAverage: Double? = 0.0
): Parcelable