package com.example.android_4_1_libraries.presenter

import com.example.android_4_1_libraries.model.profile.GithubUserProfile
import com.example.android_4_1_libraries.navigation.IScreens
import com.example.android_4_1_libraries.view.RepositoryView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

class RepositoryPresenter(
    val uiScheduler: Scheduler, val router: Router, val screens: IScreens, val repository: GithubUserProfile
) : MvpPresenter<RepositoryView>() {


    fun loadData() {

        viewState.setRepositoryName(repository.name.toString())
        viewState.setForksCount(repository.forksCount.toString())

    }


    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}