package com.example.pocsample.searchImages

import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import com.example.pocsample.R
import com.example.pocsample.SearchImagesEffect
import com.example.pocsample.architechture.BaseActivity
import com.example.pocsample.architechture.TextWatcherAdapter
import com.spotify.mobius.Next
import io.reactivex.ObservableTransformer
import kotlinx.android.synthetic.main.activity_search_images.*

class SearchImagesActivity : BaseActivity<SearchImagesModel, SearchImagesEvent, SearchImagesEffect>(),
    SearchImagesView {
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
    ): Next<SearchImagesModel, SearchImagesEffect> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



    override fun updateFunction(
        model: UserRepoModel,
        event: UserRepoEvent
    ): Next<UserRepoModel, UserRepoEffect> =
        UserRepoLogic.update(model, event)

    override fun render(model: SearchImagesModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun effectHandler(): ObservableTransformer<SearchImagesEffect, SearchImagesEvent> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}