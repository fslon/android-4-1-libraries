package com.example.android_4_1_libraries.model.profile

import io.reactivex.rxjava3.core.Single

interface IGithubUsersRepoProfile {
    fun getRepos(): Single<List<GithubUserProfile>>
}
