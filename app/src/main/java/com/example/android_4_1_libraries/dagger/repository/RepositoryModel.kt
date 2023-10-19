package com.example.android_4_1_libraries.dagger.repository

import com.example.android_4_1_libraries.model.profile.IDataSourceProfile
import com.example.android_4_1_libraries.model.profile.IGithubUsersRepoProfile
import com.example.android_4_1_libraries.model.profile.RetrofitGithubUsersRepoProfile
import com.example.android_4_1_libraries.model.room.Database
import com.example.android_4_1_libraries.model.room.cache.IRepositoriesCache
import com.example.android_4_1_libraries.model.room.cache.RoomGithubRepositoriesCache
import com.example.android_4_1_libraries.ui.network.INetworkStatus
import dagger.Module
import dagger.Provides

@Module
class RepositoryModel {

    @Provides
    fun repositoriesCache(): IRepositoriesCache =
        RoomGithubRepositoriesCache()

    @RepositoryScope
    @Provides
    fun usersRepoProfile(
        api: IDataSourceProfile, networkStatus: INetworkStatus, db: Database, cache:
        IRepositoriesCache
    ): IGithubUsersRepoProfile = RetrofitGithubUsersRepoProfile(api, networkStatus, db, cache)

}