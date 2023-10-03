package com.example.android_4_1_libraries.model.room.cache

import com.example.android_4_1_libraries.model.room.Database
import com.example.android_4_1_libraries.model.users.GithubUser
import io.reactivex.rxjava3.core.Single

interface IUsersCache {
    fun insertUsersIfOnline(
        db: Database, users: List<GithubUser>
    ): Single<List<GithubUser>>

    fun getUsersIfOffline(
        db: Database
    ): Single<List<GithubUser>>
}