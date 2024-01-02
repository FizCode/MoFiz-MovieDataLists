package dev.fizcode.mofiz.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.fizcode.mofiz.data.local.BookmarkDAO
import dev.fizcode.mofiz.database.LocalDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalStorageModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): LocalDatabase {
        return LocalDatabase.getInstance(context = context)
    }

    @Singleton
    @Provides
    fun provideMovieListDao(db: LocalDatabase): BookmarkDAO {
        return db.movieListDao()
    }
}