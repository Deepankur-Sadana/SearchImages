package com.example.pocsample.searchImages.http

import com.example.pocsample.searchImages.data.Response
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleApi {

    @GET
    fun fetchFollowers(
        @Query("q") query :String,
        @Query("cx") cx :String,
        @Query("key") apiKey :String
    ): Single<Response>
}
