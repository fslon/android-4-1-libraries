package com.example.android_4_1_libraries.model.users

import com.example.android_4_1_libraries.model.room.Database
import com.example.android_4_1_libraries.model.room.cache.IUsersCache
import com.example.android_4_1_libraries.ui.network.INetworkStatus
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(
    val api: IDataSource, val networkStatus:
    INetworkStatus, val db: Database, val cacheInterface: IUsersCache
) : IGithubUsersRepo {
    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline
        ->
        if (isOnline) {
            api.getUsers()
                .flatMap { users ->
                    cacheInterface.insertUsersIfOnline(users)
                }
        } else {
            cacheInterface.getUsersIfOffline()
        }
    }.subscribeOn(Schedulers.io())
}



