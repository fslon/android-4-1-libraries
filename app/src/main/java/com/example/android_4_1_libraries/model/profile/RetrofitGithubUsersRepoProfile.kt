package com.example.android_4_1_libraries.model.profile

import com.example.android_4_1_libraries.model.room.Database
import com.example.android_4_1_libraries.model.room.RoomGithubRepository
import com.example.android_4_1_libraries.model.users.GithubUser
import com.example.android_4_1_libraries.ui.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

//TODO Практическое задание 1 - вытащить кэширование в отдельный класс RoomRepositoriesCache и внедрить его сюда через интерфейс IRepositoriesCache

class RetrofitGithubUsersRepoProfile(val api: IDataSourceProfile, val networkStatus:
INetworkStatus, val db: Database
) : IGithubUsersRepoProfile {
    override fun getRepos(user: GithubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl?.let { url ->
                    api.getRepos(url)
                        .flatMap { repositories ->
                            Single.fromCallable {
                                val roomUser = user.login?.let {
                                    db.userDao.findByLogin(it) } ?: throw RuntimeException("No such user in cache")
                                val roomRepos = repositories.map {
                                    RoomGithubRepository(it.id ?: "", it.name ?: "", it.forksCount ?: 0,
                                        roomUser.id) }
                                db.repositoryDao.insert(roomRepos)
                                repositories
                            }
                        }
                } ?: Single.error<List<GithubUserProfile>>(RuntimeException("User has no repos url")).subscribeOn(Schedulers.io())
            } else { // TODO сделать где то тут вызов networkStatus.isOnline(), чтобы оно слушало пока сеть не появится, а когда появится загрузить данные
                Single.fromCallable {
                    val roomUser = user.login?.let { db.userDao.findByLogin(it) } ?:
                    throw RuntimeException("No such user in cache")
                    db.repositoryDao.findForUser(roomUser.id).map {
                        GithubUserProfile(it.id, it.name, it.forksCount) }
                }
            }
        }.subscribeOn(Schedulers.io())
}
