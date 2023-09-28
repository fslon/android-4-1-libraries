package com.example.android_4_1_libraries.model.profile

import com.example.android_4_1_libraries.model.users.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubUsersRepoProfile {
    fun getRepos(user: GithubUser): Single<List<GithubUserProfile>>
}
