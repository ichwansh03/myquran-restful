package com.ichwan.rest.quranapi.models

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ChapterResponse(

	@field:SerializedName("chapters")
	val chapters: List<ChaptersItem?>? = null
) : Parcelable