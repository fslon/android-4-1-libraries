package com.example.android_4_1_libraries.model.profile

import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepoProfile(val api: IDataSourceProfile): IGithubUsersRepoProfile {
    override fun getRepos() = api.getRepos().subscribeOn(Schedulers.io())
}