package com.example.pocsample.searchImages

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class SearchQuery(val query:String) : Parcelable {

    fun isValid():Boolean{
        return !query.trim().isEmpty()
    }
}