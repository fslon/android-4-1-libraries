package com.example.android_4_1_libraries.dagger

import dagger.Module
import dagger.Provides


@Module
class AppModule(val app: App) {
    @Provides
    fun app(): App {
        return app
    }
}
