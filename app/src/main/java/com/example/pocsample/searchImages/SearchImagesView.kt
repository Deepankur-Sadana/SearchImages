package com.example.pocsample.searchImages

import com.example.pocsample.searchImages.data.Response

interface SearchImagesView {

    fun disableSearchButton()
    fun showBlankMessage()
    fun enableSearchButton()
    fun showLoading()
    fun disableSearchImagesField()
    fun hideImages()
    fun hideNoImagesMessage()
    fun hideImageNotFoundMessage()
    fun hideRetryMessage()
    fun enableSearchField()
    fun hideLoading()
    fun showImages(response: Response)
}