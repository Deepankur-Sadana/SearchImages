package com.example.pocsample.searchImages.http

import com.example.pocsample.searchImages.data.Response
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GoogleApi {
    @GET("users/{user}/followers")
    fun fetchFollowers(
        @Path("user") userName: String
    ): Single<Response>
}
