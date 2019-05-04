package com.example.pocsample.threading

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.redgreen.benchpress.architecture.threading.SchedulersProvider

class DefaultSchedulersProvider : SchedulersProvider {
  override val io: Scheduler
    get() = Schedulers.io()

  override val computation: Scheduler
    get() = Schedulers.computation()

  override val ui: Scheduler
    get() = AndroidSchedulers.mainThread()
}
