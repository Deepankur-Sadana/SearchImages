package com.example.pocsample.searchImages

import com.example.pocsample.FetchingImagesFromRemoteEffect
import com.example.pocsample.searchImages.data.Response
import com.example.pocsample.searchImages.effectHandler.SearchImageEffectHandler
import com.example.pocsample.searchImages.http.GoogleApi
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test

class SearchImagesEffectHandlerTest {


    private val gitHubApi = mock<GoogleApi>()
    private val testCase = EffectHandlerTestCase(SearchImageEffectHandler.create(gitHubApi, ImmediateSchedulersProvider()))



    @Test
    fun `it can dispatch images fetched event`() {
        // given
        val query = "animals"
        val images = Response(listOf())

        whenever(gitHubApi.fetchImages(query,"dummy","dummy"))
            .thenReturn(Single.just(images))


        // when
        testCase.dispatchEffect(FetchingImagesFromRemoteEffect(query))

        // then
        testCase.assertOutgoingEvents(ImagesFetchedEvent(images))
    }
}