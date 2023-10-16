package com.example.android_4_1_libraries.model.room.cache

import com.example.android_4_1_libraries.model.room.Database
import com.example.android_4_1_libraries.model.room.RoomGithubUser
import com.example.android_4_1_libraries.model.users.GithubUser
import io.reactivex.rxjava3.core.Single

class RoomGithubUsersCache(val db: Database) : IUsersCache {
    override fun insertUsersIfOnline(users: List<GithubUser>): Single<List<GithubUser>> {
        return Single.fromCallable {
            val roomUsers = users.map { user ->
                RoomGithubUser(
                    user.id ?: "", user.login ?: "", user.avatarUrl ?: "",
                    user.reposUrl ?: ""
                )
            }
            db.userDao.insert(roomUsers)
            return@fromCallable users
        }
    }

    override fun getUsersIfOffline(): Single<List<GithubUser>> {

        return Single.fromCallable {
            db.userDao.getAll().map { roomUser ->
                GithubUser(
                    roomUser.id, roomUser.login, roomUser.avatarUrl,
                    roomUser.reposUrl
                )
            }
        }
    }
}