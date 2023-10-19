package com.example.android_4_1_libraries.dagger

import androidx.room.Room
import com.example.android_4_1_libraries.model.room.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {
    @Singleton
    @Provides
    fun database(app: App): Database = Room.databaseBuilder(
        app,
        Database::class.java, Database.DB_NAME
    )
        .build()


}

