package com.example.pocsample.searchImages

import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import com.example.pocsample.R
import com.example.pocsample.SearchImagesEffect
import com.example.pocsample.architechture.BaseActivity
import com.example.pocsample.architechture.TextWatcherAdapter
import com.example.pocsample.searchImages.effectHandler.SearchImageEffectHandler
import com.example.pocsample.searchImages.http.GoogleApi
import com.example.pocsample.threading.DefaultSchedulersProvider
import com.spotify.mobius.Next
import io.reactivex.ObservableTransformer
import kotlinx.android.synthetic.main.activity_search_images.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class SearchImagesActivity : BaseActivity<SearchImagesModel, SearchImagesEvent, SearchImagesEffect>(),
    SearchImagesView {


    private val viewRenderer by lazy(LazyThreadSafetyMode.NONE) {
        SearchImagesViewRenderer(this)
    }



    private val googgleApi by lazy(LazyThreadSafetyMode.NONE) {
        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        return@lazy Retrofit
            .Builder()
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(DefaultSchedulersProvider().io))
            .baseUrl("https://api.github.com/")
            .build()
            .create(GoogleApi::class.java)
    }

    override fun layoutResId(): Int {
        return R.layout.activity_search_images
    }

    override fun setup() {
        imageEditText.addTextChangedListener(object : TextWatcherAdapter() {
            override fun afterTextChanged(s: Editable) {
                val username = s.toString()
                eventSource.notifyEvent(
                    if (username.isBlank()) SearchQueryClearedEvent else SearchQueryChangeEvent(
                        username
                    )
                )
            }
        })

        searchButton.setOnClickListener { eventSource.notifyEvent(StartSearchingImagesEvent) }

        followersRecyclerView.layoutManager = LinearLayoutManager(this)

        retryButton.setOnClickListener { eventSource.notifyEvent(RetryFetchImagesEvent) }
    }

    override fun initialModel(): SearchImagesModel =
        SearchImagesModel.BLANK

    override fun updateFunction(
        model: SearchImagesModel,
        event: SearchImagesEvent
    ): Next<SearchImagesModel, SearchImagesEffect> = SearchImagesLogic.update(model,event)




    override fun render(model: SearchImagesModel) {
        viewRenderer.render(model)
    }

    override fun effectHandler(): ObservableTransformer<SearchImagesEffect, SearchImagesEvent> {
        return SearchImageEffectHandler.create(googgleApi, DefaultSchedulersProvider())
    }

}