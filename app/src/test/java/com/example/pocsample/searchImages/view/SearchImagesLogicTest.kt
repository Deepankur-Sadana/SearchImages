package com.example.pocsample.searchImages.view

import com.example.pocsample.SearchImagesEffect
import com.example.pocsample.searchImages.SearchImagesEvent
import com.example.pocsample.searchImages.SearchImagesLogic
import com.example.pocsample.searchImages.SearchImagesModel
import com.example.pocsample.searchImages.SearchQueryChangeEvent
import com.google.common.truth.Truth
import com.spotify.mobius.test.NextMatchers
import com.spotify.mobius.test.UpdateSpec
import org.junit.Test

class SearchImagesLogicTest {

    private val updateSpec = UpdateSpec<SearchImagesModel,
            SearchImagesEvent,
            SearchImagesEffect>(SearchImagesLogic::update)
    private val blankModel = SearchImagesModel.BLANK
    private val validQuery = "pets"


    @Test
    fun `when query is invalid, then user cannot search`() {
        val invalidQuery = ""
        val invalidModel = blankModel
            .searchQueryChanged(invalidQuery)

        updateSpec.given(blankModel)
            .`when`(SearchQueryChangeEvent(invalidQuery))
            .then(
                UpdateSpec.assertThatNext(
                    NextMatchers.hasModel(invalidModel),
                    NextMatchers.hasNoEffects()
                )
            )

        Truth.assertThat(invalidModel.isReadyToSearch)
            .isFalse()
    }



    @Test
    fun `when query is valid, then user cansearch`() {
        val invalidModel = blankModel
            .searchQueryChanged(validQuery)

        updateSpec.given(blankModel)
            .`when`(SearchQueryChangeEvent(validQuery))
            .then(
                UpdateSpec.assertThatNext(
                    NextMatchers.hasModel(invalidModel),
                    NextMatchers.hasNoEffects()
                )
            )

        Truth.assertThat(invalidModel.isReadyToSearch)
            .isTrue()
    }



}