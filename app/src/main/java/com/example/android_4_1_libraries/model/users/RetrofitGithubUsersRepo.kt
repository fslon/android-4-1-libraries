package com.example.android_4_1_libraries.model.users

import com.example.android_4_1_libraries.model.room.Database
import com.example.android_4_1_libraries.model.room.RoomGithubUser
import com.example.android_4_1_libraries.ui.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(
    val api: IDataSource, val networkStatus:
    INetworkStatus, val db: Database
) : IGithubUsersRepo {
    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline
        ->
        if (isOnline) {
            api.getUsers()
                .flatMap { users ->
                    Single.fromCallable {
                        val roomUsers = users.map { user ->
                            RoomGithubUser(
                                user.id ?: "", user.login ?: "", user.avatarUrl ?: "",
                                user.reposUrl ?: ""
                            )
                        }
                        db.userDao.insert(roomUsers)
                        users
                    }
                }
        } else {
            Single.fromCallable {
                db.userDao.getAll().map { roomUser ->
                    GithubUser(
                        roomUser.id, roomUser.login, roomUser.avatarUrl,
                        roomUser.reposUrl
                    )
                }
            }
        }
    }.subscribeOn(Schedulers.io())
}



