package com.example.android_4_1_libraries.model.profile

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface IDataSourceProfile {
    @GET()
    fun getRepos(@Url login: String): Single<List<GithubUserProfile>>

}