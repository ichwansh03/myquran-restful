package com.ichwan.rest.quranapi.domain.repository

import com.ichwan.rest.quranapi.data.ChapterConfig

class ChapterRepository {

    private val client = ChapterConfig.getChapterService()

    suspend fun getChapter(language: String) = client.getChapters(language)
}