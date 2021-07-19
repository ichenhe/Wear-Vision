@file:Suppress("DEPRECATION") // for RotaryEncoder

package me.chenhe.wearvision.util

import android.content.Context
import android.support.wearable.input.RotaryEncoder
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

internal fun isScreenRound(context: Context) = WearVision.isScreenRound(context)

internal fun View.visibleGone(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

private const val TAG = "WearVision"

internal fun loge(tag: String, msg: String) {
    Log.e(TAG, "[$tag] $msg")
}

internal fun logw(tag: String, msg: String) {
    Log.w(TAG, "[$tag] $msg")
}

fun View.postRequestFocus() {
    post { requestFocus() }
}

/**
 * Enable the support for [RSB](https://developer.android.com/training/wearables/ui/rotary-input).
 * Only when RecyclerView gets the focus can it respond to RSB scrolling.
 *
 * It is usually recommended to call [view.postRequestFocus()][postRequestFocus] in `onResume()`
 * callback.
 */
fun RecyclerView.enableRsbSupport() {
    setOnGenericMotionListener(View.OnGenericMotionListener { _, ev ->
        if (ev.action == MotionEvent.ACTION_SCROLL && RotaryEncoder.isFromRotaryEncoder(ev)) {
            val delta =
                -RotaryEncoder.getRotaryAxisValue(ev) * RotaryEncoder.getScaledScrollFactor(context)
            nestedScrollBy(0, delta.roundToInt())
            return@OnGenericMotionListener true
        }
        false
    })
}

/**
 * Enable the support for [RSB](https://developer.android.com/training/wearables/ui/rotary-input).
 * Only when RecyclerView gets the focus can it respond to RSB scrolling.
 *
 * It is usually recommended to call [view.postRequestFocus()][postRequestFocus] in `onResume()`
 * callback.
 */
fun NestedScrollView.enableRsbSupport() {
    setOnGenericMotionListener(View.OnGenericMotionListener { _, ev ->
        if (ev.action == MotionEvent.ACTION_SCROLL && RotaryEncoder.isFromRotaryEncoder(ev)) {
            val delta =
                -RotaryEncoder.getRotaryAxisValue(ev) * RotaryEncoder.getScaledScrollFactor(context)
            scrollBy(0, delta.roundToInt())
            return@OnGenericMotionListener true
        }
        false
    })
}