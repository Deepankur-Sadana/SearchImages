package com.example.pocsample.searchImages

import com.example.pocsample.threading.AsyncOp

class SearchImagesViewRenderer(
    private val view: SearchImagesView
) {

    fun render(model: SearchImagesModel) {
        val notEmpty = model.serverResponse?.items?.isNotEmpty()
        if (model.searchFollowersAsyncOp == AsyncOp.IN_FLIGHT) {
            renderLoading()
        } else if (model.searchFollowersAsyncOp == AsyncOp.SUCCEEDED && notEmpty == true) {
            renderImages(model)
        }else if (model == SearchImagesModel.BLANK) {
            renderBlank()
        }
    }

    private fun renderBlank() {
        view.disableSearchButton()
        view.showBlankMessage()
    }

    private fun renderImages(model: SearchImagesModel) {
        view.enableSearchField()
        view.enableSearchButton()
        view.hideLoading()
        view.showImages(model.serverResponse!!)

    }

    private fun renderLoading() {
        view.showLoading()
        view.disableSearchButton()
        view.disableSearchImagesField()
        view.hideImages()
        view.hideNoImagesMessage()
        view.hideImageNotFoundMessage()
        view.hideRetryMessage()
    }
}