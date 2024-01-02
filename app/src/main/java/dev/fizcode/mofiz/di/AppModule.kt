package dev.fizcode.mofiz.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.fizcode.mofiz.data.local.BookmarkDAO
import dev.fizcode.mofiz.repository.BookmarkRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideBookmarkRepository(
        bookmarkDAO: BookmarkDAO
    ): BookmarkRepository {
        return BookmarkRepository(dao = bookmarkDAO)
    }
}