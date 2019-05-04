package com.example.pocsample.searchImages.effectHandler

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
import retrofit2.HttpException
import timber.log.Timber

object SearchImageEffectHandler {
    fun create(
        gitHubApi: GoogleApi,
        schedulersProvider: SchedulersProvider
    ): ObservableTransformer<SearchImagesEffect, SearchImagesEvent> {
        return RxMobius
            .subtypeEffectHandler<SearchImagesEffect, SearchImagesEvent>()
            .addTransformer(
                SearchImagesEffect::class.java,
                makeFetchFollowersApiCall(gitHubApi, schedulersProvider)
            )
            .build()
    }

    private fun makeFetchFollowersApiCall(
        gitHubApi: GoogleApi,
        schedulersProvider: SchedulersProvider
    ): ObservableTransformer<SearchImagesEffect, SearchImagesEvent> {
        return object : ObservableTransformer<SearchImagesEffect, SearchImagesEvent> {
            override fun apply(
                searchFollowersEffects: Observable<SearchImagesEffect>
            ): ObservableSource<SearchImagesEvent> {
                return searchFollowersEffects
                    .flatMapSingle { searchImagesEffect -> gitHubApi
                        .fetchFollowers(searchImagesEffect.userName)
                        .map(::mapToFollowersEvent)
                        .doOnError(Timber::e)
                        .onErrorReturn { mapToErrorEvent(it) }
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

    private fun mapToErrorEvent(throwable: Throwable): SearchImagesEvent =
        if (throwable is HttpException && throwable.code() == 404) {
            UserNotFoundEvent
        } else {
            UnableToFetchImagesEvent
        }
}
