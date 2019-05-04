package com.example.pocsample.searchImages.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(
    @field:Json(name = "pagemap") val pageMap: PageMap
) : Parcelable


@Parcelize
data class PageMap(val cse_image: CseImage) : Parcelable


@Parcelize
data class CseImage(
    @field:Json(name = "src")
    val source: Source
) : Parcelable


@Parcelize
data class Source(val url: String) : Parcelable