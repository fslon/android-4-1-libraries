package com.example.android_4_1_libraries.dagger.user

import com.example.android_4_1_libraries.model.room.Database
import com.example.android_4_1_libraries.model.room.cache.IUsersCache
import com.example.android_4_1_libraries.model.room.cache.RoomGithubUsersCache
import com.example.android_4_1_libraries.model.users.IDataSource
import com.example.android_4_1_libraries.model.users.IGithubUsersRepo
import com.example.android_4_1_libraries.model.users.RetrofitGithubUsersRepo
import com.example.android_4_1_libraries.ui.network.INetworkStatus
import dagger.Module
import dagger.Provides


@Module
class UserModule {

    @Provides
    fun usersCache(database: Database): IUsersCache =
        RoomGithubUsersCache(database)

    @UserScope
    @Provides
     fun usersRepo(
        api: IDataSource, networkStatus: INetworkStatus, db: Database, cache:
        IUsersCache
    ): IGithubUsersRepo = RetrofitGithubUsersRepo(api, networkStatus, db, cache)

}