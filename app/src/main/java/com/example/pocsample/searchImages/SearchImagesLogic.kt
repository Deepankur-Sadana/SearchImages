package com.example.pocsample.searchImages

import com.example.pocsample.FetchingImagesFromRemoteEffect
import com.example.pocsample.SearchImagesEffect
import com.spotify.mobius.Next
import com.spotify.mobius.Update

object SearchImagesLogic : Update<SearchImagesModel, SearchImagesEvent, SearchImagesEffect> {
    override fun update(
        model: SearchImagesModel,
        event: SearchImagesEvent
    ): Next<SearchImagesModel, SearchImagesEffect> {
        return when (event) {

            is SearchQueryChangeEvent -> Next.next(model.searchQueryChanged(event.userName))
            is UnableToFetchImagesEvent ->  Next.next(model.unableToFetchImages())

            is StartSearchingImagesEvent -> Next.next(
                model.startSearchingImages(), setOf(FetchingImagesFromRemoteEffect(model.searchQuery.query))
            )

            else -> TODO()
        }
    }
}

