package com.example.android_4_1_libraries.view.glide

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}
