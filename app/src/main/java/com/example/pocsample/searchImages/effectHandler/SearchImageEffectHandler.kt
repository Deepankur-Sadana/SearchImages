package com.example.pocsample.searchImages.effectHandler

import com.example.pocsample.FetchingImagesFromRemoteEffect
import com.example.pocsample.SearchImagesEffect
import com.example.pocsample.searchImages.ImagesFetchedEvent
import com.example.pocsample.searchImages.NoImagesFoundEvent
import com.example.pocsample.searchImages.SearchImagesEvent
import com.example.pocsample.searchImages.UnableToFetchImagesEvent
import com.example.pocsample.searchImages.data.Response
import com.example.pocsample.searchImages.http.GoogleApi
import com.spotify.mobius.rx2.RxMobius
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.redgreen.benchpress.architecture.threading.SchedulersProvider
import timber.log.Timber

object SearchImageEffectHandler {
    fun create(
        gitHubApi: GoogleApi,
        schedulersProvider: SchedulersProvider
    ): ObservableTransformer<SearchImagesEffect, SearchImagesEvent> {
        return RxMobius
            .subtypeEffectHandler<SearchImagesEffect, SearchImagesEvent>()
            .addTransformer(
                FetchingImagesFromRemoteEffect::class.java,
                makeFetchFollowersApiCall(gitHubApi, schedulersProvider)
            )
            .build()
    }

    private fun makeFetchFollowersApiCall(
        gitHubApi: GoogleApi,
        schedulersProvider: SchedulersProvider
    ): ObservableTransformer<FetchingImagesFromRemoteEffect, SearchImagesEvent> {
        return object : ObservableTransformer<FetchingImagesFromRemoteEffect, SearchImagesEvent> {
            override fun apply(
                searchFollowersEffects: Observable<FetchingImagesFromRemoteEffect>
            ): ObservableSource<SearchImagesEvent> {
                return searchFollowersEffects
                    .flatMapSingle { searchImagesEffect ->
                        gitHubApi
                            .fetchFollowers(searchImagesEffect.imageQuery)
                            .map(::mapToFollowersEvent)
                            .doOnError(Timber::e)
                            .onErrorReturn { UnableToFetchImagesEvent }
                    }
                    .subscribeOn(schedulersProvider.io)
            }
        }
    }

    private fun mapToFollowersEvent(response: Response): SearchImagesEvent =
        if (response.items.isEmpty()) {
            NoImagesFoundEvent
        } else ImagesFetchedEvent(
            response
        )

}
