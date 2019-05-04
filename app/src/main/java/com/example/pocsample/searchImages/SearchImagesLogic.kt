package com.example.pocsample.searchImages

import com.example.pocsample.SearchImagesEffect
import com.spotify.mobius.Next
import com.spotify.mobius.Update

object SearchImagesLogic : Update<SearchImagesModel, SearchImagesEvent, SearchImagesEffect> {
    override fun update(
        model: SearchImagesModel,
        event: SearchImagesEvent
    ): Next<SearchImagesModel, SearchImagesEffect> {
        return when (event) {

            is SearchQueryChangeEvent -> Next.next(model.searchQueryChanged(event.userName))

            else -> TODO()
        }
    }
}


object UserRepoLogic : Update<UserRepoModel, UserRepoEvent, UserRepoEffect> {
    override fun update(
        model: UserRepoModel,
        event: UserRepoEvent
    ): Next<UserRepoModel, UserRepoEffect> {

        return when (event) {
            is UserNameChangeEvent -> Next.next(model.userNameChanged(event.userName))
            is UserNameClearedEvent -> Next.next(model.userNameCleared())
            is SearchFollowersEvent -> Next.next(
                model.searchFollowers(), setOf(SearchFollowersEffect(model.userName.name))
            )
            is UnableToFetchFollowersEvent -> Next.next(model.unableToFetchFollowers())
            is NoFollowersFoundEvent -> Next.next(model.noFollowersFound())
            is UserNotFoundEvent -> Next.next(model.userNotFound())
            is FollowersFetchedEvent -> Next.next(model.followersListFetched(event.followers))
            is RetryFetchFollowersEvent -> Next.next(
                model.searchFollowers(), setOf(SearchFollowersEffect(model.userName.name))
            )
            else -> TODO()
        }
    }
}
