package com.ichwan.rest.quranapi.models

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ChapterResponse(

	@field:SerializedName("chapters")
	val chapters: MutableList<ChaptersItem>
) : Parcelable

@Parcelize
data class ChaptersItem(

	@field:SerializedName("translated_name")
	val translatedName: TranslatedName? = null,

	@field:SerializedName("revelation_order")
	val revelationOrder: Int? = null,

	@field:SerializedName("verses_count")
	val versesCount: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("name_simple")
	val nameSimple: String? = null,

	@field:SerializedName("revelation_place")
	val revelationPlace: String? = null
) : Parcelable


@Parcelize
data class TranslatedName(

	@field:SerializedName("name")
	val name: String? = null,

	) : Parcelable