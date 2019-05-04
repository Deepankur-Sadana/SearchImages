package com.example.pocsample.searchImages

import com.example.pocsample.searchImages.data.Response

sealed class SearchImagesEvent

data class SearchQueryChangeEvent(val userName: String) : SearchImagesEvent()

object SearchQueryClearedEvent : SearchImagesEvent()

//object SearchImagesEvent : SearchImageEvent()

data class ImagesFetchedEvent(val serverResponse: Response) : SearchImagesEvent()

object NoImagesFoundEvent : SearchImagesEvent()


object UnableToFetchImagesEvent : SearchImagesEvent()

object RetryFetchImagesEvent : SearchImagesEvent()
