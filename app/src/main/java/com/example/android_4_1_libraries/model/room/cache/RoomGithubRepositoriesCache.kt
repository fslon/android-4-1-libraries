package com.example.android_4_1_libraries.model.room.cache

import com.example.android_4_1_libraries.model.profile.GithubUserProfile
import com.example.android_4_1_libraries.model.room.Database
import com.example.android_4_1_libraries.model.room.RoomGithubRepository
import com.example.android_4_1_libraries.model.users.GithubUser
import io.reactivex.rxjava3.core.Single

class RoomGithubRepositoriesCache : IRepositoriesCache {

    override fun insertRepositoriesIfOnline(
        db: Database, user: GithubUser, repositories: List<GithubUserProfile>
    ): Single<List<GithubUserProfile>> {
        return Single.fromCallable {
            val roomUser = user.login?.let {
                db.userDao.findByLogin(it)
            } ?: throw RuntimeException("No such user in cache")
            val roomRepos = repositories.map {
                RoomGithubRepository(
                    it.id ?: "", it.name ?: "", it.forksCount ?: 0,
                    roomUser.id
                )
            }
            db.repositoryDao.insert(roomRepos)
            return@fromCallable repositories
        }
    }

    override fun getRepositoriesIfOffline(db: Database, user: GithubUser): Single<List<GithubUserProfile>> {
        return Single.fromCallable {
            val roomUser = user.login?.let { db.userDao.findByLogin(it) } ?: throw RuntimeException("No such user in cache")
            db.repositoryDao.findForUser(roomUser.id).map {
                GithubUserProfile(it.id, it.name, it.forksCount)
            }
        }
    }

}