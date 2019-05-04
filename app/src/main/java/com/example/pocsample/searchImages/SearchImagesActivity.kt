package com.example.pocsample.searchImages

import com.example.pocsample.SearchImagesEffect
import com.example.pocsample.architechture.BaseActivity
import com.spotify.mobius.Next
import io.reactivex.ObservableTransformer

class SearchImagesActivity : BaseActivity<SearchImagesModel, SearchImagesEvent, SearchImagesEffect>(),SearchImagesView  {
    override fun layoutResId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setup() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initialModel(): SearchImagesModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateFunction(
        model: SearchImagesModel,
        event: SearchImagesEvent
    ): Next<SearchImagesModel, SearchImagesEffect> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun render(model: SearchImagesModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun effectHandler(): ObservableTransformer<SearchImagesEffect, SearchImagesEvent> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}