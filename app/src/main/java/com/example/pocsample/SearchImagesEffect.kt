package com.example.pocsample

sealed class SearchImagesEffect

data class FetchingImagesFromRemoteEffect(
    val imageQuery: String
) : SearchImagesEffect()
