package com.example.android_4_1_libraries.model.room.cache

import com.example.android_4_1_libraries.model.users.GithubUser
import io.reactivex.rxjava3.core.Single

interface IUsersCache {
    fun insertUsersIfOnline(
        users: List<GithubUser>
    ): Single<List<GithubUser>>

    fun getUsersIfOffline(
    ): Single<List<GithubUser>>
}