package com.example.pocsample.searchImages

import io.reactivex.ObservableTransformer
import io.reactivex.subjects.PublishSubject

class EffectHandlerTestCase<F, E>(
    effectHandler: ObservableTransformer<F, E>
) {
  private val incomingEffectsSubject = PublishSubject.create<F>()
  private val outgoingEffectsTestObserver = incomingEffectsSubject.compose(effectHandler).test()

  fun dispatchEffect(effect: F) {
    incomingEffectsSubject.onNext(effect)
  }

  fun assertOutgoingEvents(vararg events: E) {
    outgoingEffectsTestObserver.assertValues(*events)
  }

  fun assertNoOutgoingEvents() {
    outgoingEffectsTestObserver.assertNoValues()
  }
}
