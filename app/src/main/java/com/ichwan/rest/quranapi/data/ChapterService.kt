package com.ichwan.rest.quranapi.data

import com.ichwan.rest.quranapi.models.ChapterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ChapterService {

    @GET("chapters")
    suspend fun getChapters(
        @Query("language") language: String?
    ): Response<ChapterResponse>

}