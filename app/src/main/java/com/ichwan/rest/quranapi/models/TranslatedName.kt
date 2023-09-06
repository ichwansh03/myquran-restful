package com.ichwan.rest.quranapi.models

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class TranslatedName(

	@field:SerializedName("name")
	val name: String? = null,

) : Parcelable