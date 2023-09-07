package com.ichwan.rest.quranapi.domain.listener

import com.ichwan.rest.quranapi.models.ChaptersItem

interface OnListClickListener {

    fun onChapterClick(chaptersItem: ChaptersItem)
}