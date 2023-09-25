package com.example.android_4_1_libraries.model.profile

import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepoProfile(val api: IDataSourceProfile, val login: String): IGithubUsersRepoProfile {
    override fun getRepos() = api.getRepos(login).subscribeOn(Schedulers.io())
}