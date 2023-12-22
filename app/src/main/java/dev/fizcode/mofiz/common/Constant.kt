package dev.fizcode.mofiz.common

import dev.fizcode.mofiz.BuildConfig

object Constant {
    object ApiKey {
        const val API_KEY = BuildConfig.API_KEY
    }
    object Named {
        const val BASE_URL = "BASE_URL"
        const val RETROFIT = "RETROFIT"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    }
}