package com.example.pocsample.searchImages.effectHandler

import com.example.pocsample.SearchImagesEffect
import com.example.pocsample.searchImages.SearchImagesEvent
import com.example.pocsample.searchImages.http.GoogleApi
import com.spotify.mobius.rx2.RxMobius
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.redgreen.benchpress.architecture.threading.SchedulersProvider
import io.redgreen.benchpress.userrepo.*
import io.redgreen.benchpress.userrepo.http.GitHubApi
import retrofit2.HttpException
import timber.log.Timber

object SearchImageEffectHandler {
    fun create(
        gitHubApi: GitHubApi,
        schedulersProvider: SchedulersProvider
    ): ObservableTransformer<UserRepoEffect, UserRepoEvent> {
        return RxMobius
            .subtypeEffectHandler<UserRepoEffect, UserRepoEvent>()
            .addTransformer(
                SearchFollowersEffect::class.java,
                makeFetchFollowersApiCall(gitHubApi, schedulersProvider)
            )
            .build()
    }

    private fun makeFetchFollowersApiCall(
        gitHubApi: GoogleApi,
        schedulersProvider: SchedulersProvider
    ): ObservableTransformer<SearchImagesEffect, SearchImagesEvent> {
        return object : ObservableTransformer<SearchFollowersEffect, SearchImagesEvent> {
            override fun apply(
                searchFollowersEffects: Observable<SearchImagesEffect>
            ): ObservableSource<SearchImagesEvent> {
                return searchFollowersEffects
                    .flatMapSingle { searchFollowersEffect -> gitHubApi
                        .fetchFollowers(searchFollowersEffect.userName)
                        .map(::mapToFollowersEvent)
                        .doOnError(Timber::e)
                        .onErrorReturn { mapToErrorEvent(it) }
                    }
                    .subscribeOn(schedulersProvider.io)
            }
        }
    }

    private fun mapToFollowersEvent(followers: List<User>): UserRepoEvent =
        if (followers.isEmpty()) {
            NoFollowersFoundEvent
        } else FollowersFetchedEvent(
            followers
        )

    private fun mapToErrorEvent(throwable: Throwable): UserRepoEvent =
        if (throwable is HttpException && throwable.code() == 404) {
            UserNotFoundEvent
        } else {
            UnableToFetchFollowersEvent
        }
}
