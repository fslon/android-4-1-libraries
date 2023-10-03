package com.example.android_4_1_libraries.model.room.cache

import com.example.android_4_1_libraries.model.profile.GithubUserProfile
import com.example.android_4_1_libraries.model.room.Database
import com.example.android_4_1_libraries.model.users.GithubUser
import io.reactivex.rxjava3.core.Single

interface IRepositoriesCache {
    fun insertRepositoriesIfOnline(
        db: Database, user: GithubUser, repositories: List<GithubUserProfile>
    ): Single<List<GithubUserProfile>>

    fun getRepositoriesIfOffline(
        db: Database, user: GithubUser
    ): Single<List<GithubUserProfile>>

}