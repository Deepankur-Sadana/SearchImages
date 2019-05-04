package com.example.pocsample

sealed class SearchImagesEffect

data class StartSearchingImagesEffect(
    val imageQuery: String
) : SearchImagesEffect()
