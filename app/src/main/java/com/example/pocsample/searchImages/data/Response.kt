package com.example.pocsample.searchImages.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Response (val items :List<Image> ) : Parcelable