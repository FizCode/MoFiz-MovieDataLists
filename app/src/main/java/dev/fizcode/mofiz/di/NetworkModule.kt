package dev.fizcode.mofiz.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.fizcode.mofiz.common.Constant
import dev.fizcode.mofiz.data.api.MovieDetailsAPI
import dev.fizcode.mofiz.data.api.MovieListAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    @Named(Constant.Named.BASE_URL)
    fun provideBaseUrl(): String = "https://api.themoviedb.org/3/"

    @Singleton
    @Provides
    fun provideHttpLogging(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        return httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Singleton
    @Provides
    @Named(Constant.Named.RETROFIT)
    fun provideRetrofit(
        @Named(Constant.Named.BASE_URL) baseUrl: String,
        client: OkHttpClient
    ) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieListAPI(
        @Named(Constant.Named.RETROFIT) retrofit: Retrofit
    ): MovieListAPI {
        return retrofit.create(MovieListAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideMovieDetailsAPI(
        @Named(Constant.Named.RETROFIT) retrofit: Retrofit
    ): MovieDetailsAPI {
        return retrofit.create(MovieDetailsAPI::class.java)
    }
}