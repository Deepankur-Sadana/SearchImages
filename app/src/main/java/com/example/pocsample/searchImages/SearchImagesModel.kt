package com.example.pocsample.searchImages

import android.os.Parcelable
import com.example.pocsample.threading.AsyncOp
import com.example.pocsample.searchImages.SearchImagesError.UNKNOWN
import kotlinx.android.parcel.Parcelize


@Parcelize
data class SearchImagesModel (
    val searchFollowersAsyncOp: AsyncOp,
    val searchQuery: SearchQuery,
    val error: SearchImagesError
) : Parcelable {


    companion object {
        val BLANK = SearchImagesModel(
            searchFollowersAsyncOp = AsyncOp.IDLE,
            searchQuery = SearchQuery(""),
            error = SearchImagesError.NONE
        )
    }


    fun searchQueryChanged(query: String): SearchImagesModel = copy(searchQuery = SearchQuery(query))



    fun unableToFetchImages(): SearchImagesModel =
        copy(searchFollowersAsyncOp = AsyncOp.FAILED, error = UNKNOWN)

    fun startSearchingImages(): SearchImagesModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}