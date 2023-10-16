package com.example.android_4_1_libraries.presenter

import com.example.android_4_1_libraries.dagger.App
import com.example.android_4_1_libraries.model.users.GithubUser
import com.example.android_4_1_libraries.model.users.IGithubUsersRepo
import com.example.android_4_1_libraries.navigation.IScreens
import com.example.android_4_1_libraries.presenter.list.IUserListPresenter
import com.example.android_4_1_libraries.view.UsersView
import com.example.android_4_1_libraries.view.list.UserItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter
import javax.inject.Inject

class UsersPresenter : MvpPresenter<UsersView>() {

    @Inject
    lateinit var usersRepo: IGithubUsersRepo
    @Inject
    lateinit var router: Router
    @Inject
    lateinit var screens: IScreens

    class UsersListPresenter : IUserListPresenter {

        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun getCount() = users.size
        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login?.let { view.setLogin(it) }
            user.avatarUrl?.let { view.loadAvatar(it) }

        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        App.instance.appComponent.inject(this)

        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
            val user = usersListPresenter.users[itemView.pos]

            router.navigateTo(screens.profileUser(user)) // переход на экран пользователя c помощью router.navigateTo
        }
    }

    fun loadData() {
        usersRepo.getUsers().observeOn(AndroidSchedulers.mainThread()).subscribe({ repos ->
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

