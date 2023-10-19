package com.example.android_4_1_libraries.dagger

import android.widget.ImageView
import com.example.android_4_1_libraries.view.glide.GlideImageLoader
import com.example.android_4_1_libraries.view.glide.IImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageModule {
    @Singleton
    @Provides
    fun imageLoader(): IImageLoader<ImageView> = GlideImageLoader()


}
