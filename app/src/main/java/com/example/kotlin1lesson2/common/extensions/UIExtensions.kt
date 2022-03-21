package com.example.kotlin1lesson2.common.extensions

import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide

fun ImageView.setImage(url: String) =
    Glide.with(this)
        .load(url)
        .into(this)

val name = MutableLiveData<String>()
val image = MutableLiveData<String>()


fun putName(text: String) {
    name.value = text
}

fun putDescription(text: String) {
    image.value = text
}