package com.shrouk.dailyforecast.forecastExt

import android.view.View

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun View.makeInVisible() {
    this.visibility = View.INVISIBLE
}

fun View.makeGone() {
    this.visibility = View.GONE
}

fun View.enableView() {
    this.isClickable = true
    this.isEnabled = true
}

fun View.disableView() {
    this.isClickable = false
    this.isEnabled = false
}