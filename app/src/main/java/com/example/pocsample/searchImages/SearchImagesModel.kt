package com.example.pocsample.searchImages

import android.os.Parcelable
import com.example.pocsample.threading.AsyncOp
import kotlinx.android.parcel.Parcelize


@Parcelize
data class SearchImagesModel (
    val searchFollowersAsyncOp: AsyncOp,
    val searchQuery: SearchQuery
    ) : Parcelable {


    companion object {
        val BLANK = SearchImagesModel(
            searchQuery = SearchQuery(""),
            searchFollowersAsyncOp = AsyncOp.IDLE
        )
    }


    fun searchQueryChanged(query: String): SearchImagesModel = copy(searchQuery = SearchQuery(query))


}