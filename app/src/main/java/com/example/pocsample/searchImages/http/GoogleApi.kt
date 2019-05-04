package com.example.pocsample.searchImages.http

import com.example.pocsample.searchImages.data.Response
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GoogleApi {
    @GET("users/{user}/followers")
    fun fetchFollowers(
        @Path("user") userName: String
    ): Single<Response>


    //https://www.googleapis.com/customsearch/v1
    // ?
    // q=harrypotter
    // &
    // cx=011476162607576381860:ra4vmliv9ti
    // &
    // key=AIzaSyCjp0DYfdhyfgoroAx8UogSLfk1FlfV1eM
    @GET
    fun fetchFollowers(
        @Query("q") query :String,
        @Query("cx") cx :String,
        @Query("key") apiKey :String
    )

//    getProducts(@Query("one") String one, @Query("two") String two,
//    @Query("key") String key)
}
