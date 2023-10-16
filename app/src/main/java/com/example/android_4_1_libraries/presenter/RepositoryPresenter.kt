package com.example.android_4_1_libraries.presenter

import com.example.android_4_1_libraries.dagger.App
import com.example.android_4_1_libraries.model.profile.GithubUserProfile
import com.example.android_4_1_libraries.view.RepositoryView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class RepositoryPresenter(
    val repository: GithubUserProfile
) : MvpPresenter<RepositoryView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.instance.appComponent.inject(this)
    }

    fun loadData() {
        viewState.setRepositoryName(repository.name.toString())
        viewState.setForksCount(repository.forksCount.toString())
    }


    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}