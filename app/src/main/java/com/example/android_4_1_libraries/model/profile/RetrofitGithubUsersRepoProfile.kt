package com.example.android_4_1_libraries.model.profile

import com.example.android_4_1_libraries.model.room.Database
import com.example.android_4_1_libraries.model.room.cache.IRepositoriesCache
import com.example.android_4_1_libraries.model.users.GithubUser
import com.example.android_4_1_libraries.ui.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepoProfile(
    val api: IDataSourceProfile, val networkStatus:
    INetworkStatus, val db: Database, val cacheInterface: IRepositoriesCache
) : IGithubUsersRepoProfile {

    override fun getRepos(user: GithubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {

                user.reposUrl?.let { url ->
                    api.getRepos(url).flatMap { repositories ->
                        cacheInterface.insertRepositoriesIfOnline(db, user, repositories)
                    }
                } ?: Single.error<List<GithubUserProfile>>(RuntimeException("User has no repos url")).subscribeOn(Schedulers.io())

            } else { // TODO сделать где то тут вызов networkStatus.isOnline(), чтобы оно слушало пока сеть не появится, а когда появится, загрузить данные
                cacheInterface.getRepositoriesIfOffline(db, user)
            }
        }.subscribeOn(Schedulers.io())
}
