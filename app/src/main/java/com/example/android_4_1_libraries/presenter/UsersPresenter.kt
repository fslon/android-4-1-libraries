package com.example.android_4_1_libraries.presenter

import com.example.android_4_1_libraries.model.GithubUser
import com.example.android_4_1_libraries.model.IGithubUsersRepo
import com.example.android_4_1_libraries.navigation.IScreens
import com.example.android_4_1_libraries.presenter.list.IUserListPresenter
import com.example.android_4_1_libraries.view.UsersView
import com.example.android_4_1_libraries.view.list.UserItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

class UsersPresenter(
    val uiScheduler: Scheduler, val usersRepo:
    IGithubUsersRepo, val router: Router, val screens: IScreens
) : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {

        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun getCount() = users.size
        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login?.let { view.setLogin(it) }
            user.avatarUrl?.let {view.loadAvatar(it)}

        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
            val user = usersListPresenter.users[itemView.pos]

            router.navigateTo(screens.profileUser(user)) // переход на экран пользователя c помощью router.navigateTo
        }
    }

    fun loadData() {
        usersRepo.getUsers().observeOn(uiScheduler).subscribe({ repos ->
            usersListPresenter.users.clear()
            usersListPresenter.users.addAll(repos)
            viewState.updateList()

        }, {
            println("Error: ${it.message}")

        })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        viewState.release()
    }
}

