package com.example.android_4_1_libraries.presenter

import com.example.android_4_1_libraries.model.CountersModel
import com.example.android_4_1_libraries.model.GithubUser
import com.example.android_4_1_libraries.model.GithubUsersRepo
import com.example.android_4_1_libraries.presenter.list.IUserListPresenter
import com.example.android_4_1_libraries.view.MainView
import com.example.android_4_1_libraries.view.list.UserItemView
import moxy.MvpPresenter

class MainPresenter(val usersRepo: GithubUsersRepo) : MvpPresenter<MainView>() {

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
           //TODO: переход на экран пользователя
        }
    }

    fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

}

