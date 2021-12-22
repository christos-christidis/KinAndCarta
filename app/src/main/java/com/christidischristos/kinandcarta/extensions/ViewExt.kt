package com.christidischristos.kinandcarta.extensions

import android.view.View
import android.widget.TextView

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.visibleOrGone(visible: Boolean) {
    if (visible) {
        visible()
    } else {
        gone()
    }
}

fun TextView.visible(s: CharSequence) {
    visibility = View.VISIBLE
    text = s
}

