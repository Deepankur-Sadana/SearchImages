package com.example.pocsample.searchImages

import android.os.Parcelable
import com.example.pocsample.threading.AsyncOp
import com.example.pocsample.searchImages.SearchImagesError.UNKNOWN
import com.example.pocsample.searchImages.SearchImagesError.NONE
import com.example.pocsample.searchImages.data.Response
import kotlinx.android.parcel.Parcelize


@Parcelize
data class SearchImagesModel (
    val searchFollowersAsyncOp: AsyncOp,
    val searchQuery: SearchQuery,
    val error: SearchImagesError,
    val serverResponse: Response?
) : Parcelable {

    companion object {
        val BLANK = SearchImagesModel(
            searchFollowersAsyncOp = AsyncOp.IDLE,
            searchQuery = SearchQuery(""),
            error = SearchImagesError.NONE,
            serverResponse = null
        )
    }

    fun searchQueryChanged(query: String): SearchImagesModel = copy(searchQuery = SearchQuery(query))


    fun unableToFetchImages(): SearchImagesModel =
        copy(searchFollowersAsyncOp = AsyncOp.FAILED, error = UNKNOWN)

    fun startSearchingImages(): SearchImagesModel =
        copy(searchFollowersAsyncOp = AsyncOp.IN_FLIGHT, error = NONE)


    fun imagesNotFound(): SearchImagesModel =
        copy(searchFollowersAsyncOp = AsyncOp.SUCCEEDED, error = SearchImagesError.IMAGES_NOT_FOUND)

}