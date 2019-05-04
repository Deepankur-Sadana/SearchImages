package com.example.pocsample.searchImages.view

import com.example.pocsample.searchImages.SearchImagesModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Assert.*
import org.junit.Test

class SearchImagesViewRendererTest{
    private val view = mock<SearchImagesView>()
    private val viewRenderer = SearchImagesViewRenderer(view)

    @Test
    fun `it can render blank view state`() {
        // given
        val blankModel = SearchImagesModel.BLANK

        // when
        viewRenderer.render(blankModel)

        // then
        verify(view).disableSearchButton()
        verify(view).showBlankMessage()

        verifyNoMoreInteractions(view)
    }


    @Test
    fun `it can render ready to search state`() {
        // given
        val readyToSearchModel = SearchImagesModel.BLANK
            .searchQueryChanged("deepankur")

        // when
        viewRenderer.render(readyToSearchModel)

        // then
        verify(view).enableSearchButton()

        verifyNoMoreInteractions(view)
    }
}