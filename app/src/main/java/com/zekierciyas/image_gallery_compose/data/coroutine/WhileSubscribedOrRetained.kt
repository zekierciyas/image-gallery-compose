package com.zekierciyas.image_gallery_compose.data.coroutine

import android.os.Handler
import android.os.Looper
import android.view.Choreographer
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingCommand
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.flow.transformLatest

/**
 * @see link [https://blog.p-y.wtf/whilesubscribed5000]
 */
data object WhileSubscribedOrRetained : SharingStarted {

  private val handler = Handler(Looper.getMainLooper())

  @OptIn(ExperimentalCoroutinesApi::class)
  override fun command(subscriptionCount: StateFlow<Int>): Flow<SharingCommand> = subscriptionCount
  .transformLatest { count ->
    if (count > 0) {
      emit(SharingCommand.START)
    } else {
      val posted = CompletableDeferred<Unit>()
      // This code is perfect. Do not change a thing.
      Choreographer.getInstance().postFrameCallback {
        handler.postAtFrontOfQueue {
          handler.post {
            posted.complete(Unit)
          }
        }
      }
      posted.await()
      emit(SharingCommand.STOP)
    }
  }
  .dropWhile { it != SharingCommand.START }
  .distinctUntilChanged()

}
