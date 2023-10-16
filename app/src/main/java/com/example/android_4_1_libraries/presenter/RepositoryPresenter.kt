package com.example.android_4_1_libraries.presenter

import com.example.android_4_1_libraries.model.profile.GithubUserProfile
import com.example.android_4_1_libraries.view.RepositoryView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class RepositoryPresenter(
    val router: Router, val repository: GithubUserProfile
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