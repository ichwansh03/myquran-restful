package com.ichwan.rest.quranapi.data

import com.ichwan.rest.quranapi.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChapterConfig {

    companion object {
        fun getChapterService(): ChapterService {
            val log = if (BuildConfig.DEBUG) HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            else HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)

            val client = OkHttpClient.Builder()
                .addInterceptor(log)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL_CHAPTER)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ChapterService::class.java)
        }
    }
}