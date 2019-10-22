package com.mario.examplemvvm.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import coil.api.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun ViewGroup.inflateLayout(@LayoutRes layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun ImageView.loadFromUrlCircle(image: String) {
    this.load(image) {
        transformations(CircleCropTransformation())
        target {
            setImageDrawable(it)
        }
    }
}

fun ImageView.loadFromUrlRoundedCorners(image: String) {
    this.load(image) {
        transformations(RoundedCornersTransformation(1f))
        target {
            setImageDrawable(it)
        }
    }
}