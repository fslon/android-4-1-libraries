package com.example.android_4_1_libraries.model.profile

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface IDataSourceProfile {
    @GET("/users/mojombo/repos") //TODO исправить хардкод
    fun getRepos(): Single<List<GithubUserProfile>>

}