package com.example.android_4_1_libraries.dagger

import android.app.Application
import com.example.android_4_1_libraries.dagger.repository.RepositorySubComponent
import com.example.android_4_1_libraries.dagger.user.UserSubComponent


class App : Application() {
    lateinit var appComponent: AppComponent
        private set

    var userSubComponent: UserSubComponent? = null
        private set

    var repositorySubComponent: RepositorySubComponent? = null
        private set

    companion object {
        lateinit var instance: App
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun initUserSubComponent() = appComponent.userSubcomponent().also {
        userSubComponent = it
    }

    fun releaseUserSubComponent() {
        userSubComponent = null
    }

    fun initRepositorySubComponent() = userSubComponent?.repositorySubComponent().also {
        repositorySubComponent = it
    }

    fun releaseRepositorySubComponent() {
        repositorySubComponent = null
    }


}

