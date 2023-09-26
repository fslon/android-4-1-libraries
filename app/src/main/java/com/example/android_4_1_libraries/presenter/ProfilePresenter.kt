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


        setOnClickListener()

    }

    private fun setOnClickListener() {
        profileListPresenter.itemClickListener = { itemView ->
            val repository = profileListPresenter.repositories[itemView.pos]

            router.navigateTo(screens.repository(repository)) // переход на экран репозитория c помощью router.navigateTo
        }
    }

    private fun loadData() {

        user.login?.let { viewState.setUserLogin(it) }

        usersRepo.getRepos().observeOn(uiScheduler).subscribe({ repos ->
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