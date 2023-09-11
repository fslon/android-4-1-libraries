package com.example.android_4_1_libraries.presenter

import com.example.android_4_1_libraries.model.GithubUser
import com.example.android_4_1_libraries.view.ProfileView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class ProfilePresenter(val router: Router, val user: GithubUser) : MvpPresenter<ProfileView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData()
    }

    private fun loadData() {
        // todo получение логина пользователя (придумать как сделать)

        viewState.setUserLogin(user.login)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}