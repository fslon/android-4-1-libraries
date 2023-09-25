package com.example.android_4_1_libraries.presenter

import com.example.android_4_1_libraries.model.GithubUser
import com.example.android_4_1_libraries.model.profile.GithubUserProfile
import com.example.android_4_1_libraries.model.profile.IGithubUsersRepoProfile
import com.example.android_4_1_libraries.navigation.IScreens
import com.example.android_4_1_libraries.presenter.listProfile.IUserListPresenterProfile
import com.example.android_4_1_libraries.view.ProfileView
import com.example.android_4_1_libraries.view.listProfile.UserItemViewProfile
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

class ProfilePresenter(
    val uiScheduler: Scheduler, val usersRepo:
    IGithubUsersRepoProfile, val router: Router, val screens: IScreens, val user: GithubUser
) : MvpPresenter<ProfileView>() {

    class ProfileListPresenter : IUserListPresenterProfile {

        val repositories = mutableListOf<GithubUserProfile>()

        override var itemClickListener: ((UserItemViewProfile) -> Unit)? = null
        override fun getCount() = repositories.size
        override fun bindView(view: UserItemViewProfile) {
            val repository = repositories[view.pos]
            repository.name?.let { view.setRepositoryName(it) }

        }
    }

    val profileListPresenter = ProfilePresenter.ProfileListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

        loadData()

//        usersListPresenter.itemClickListener = { itemView -> TODO переход на экран с инфой о репозитории
//            val user = usersListPresenter.users[itemView.pos]
//
//            router.navigateTo(screens.profileUser(user)) // переход на экран пользователя c помощью router.navigateTo
//        }
    }

    private fun loadData() {

//        viewState.setUserLogin(user.login)
        user.login?.let { viewState.setUserLogin(it) }

        usersRepo.getRepos().observeOn(uiScheduler).subscribe({ repos -> // TODO переделать код
            profileListPresenter.repositories.clear()
            profileListPresenter.repositories.addAll(repos)
            viewState.updateList()

        }, {
            println("Error: ${it.message}")

        })

    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}