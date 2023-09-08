package com.example.android_4_1_libraries.presenter

import com.example.android_4_1_libraries.view.ProfileView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class ProfilePresenter(val router: Router) : MvpPresenter<ProfileView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData()
    }

    private fun loadData() {
        // todo получение логина пользователя (придумать как сделать)

        val userName = "TEST USERNAME" // [тестовый логин]

        viewState.setUser(userName)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}