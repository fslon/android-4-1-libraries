package com.example.android_4_1_libraries.presenter

import com.example.android_4_1_libraries.dagger.App
import com.example.android_4_1_libraries.model.profile.GithubUserProfile
import com.example.android_4_1_libraries.model.profile.IGithubUsersRepoProfile
import com.example.android_4_1_libraries.model.users.GithubUser
import com.example.android_4_1_libraries.navigation.IScreens
import com.example.android_4_1_libraries.presenter.listProfile.IUserListPresenterProfile
import com.example.android_4_1_libraries.view.ProfileView
import com.example.android_4_1_libraries.view.listProfile.UserItemViewProfile
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter
import javax.inject.Inject

class ProfilePresenter(val user: GithubUser) : MvpPresenter<ProfileView>() {

    class ProfileListPresenter : IUserListPresenterProfile {

        val repositories = mutableListOf<GithubUserProfile>()

        override var itemClickListener: ((UserItemViewProfile) -> Unit)? = null
        override fun getCount() = repositories.size
        override fun bindView(view: UserItemViewProfile) {
            val repository = repositories[view.pos]
            repository.name?.let { view.setRepositoryName(it) }
        }
    }

    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var usersRepo: IGithubUsersRepoProfile


    val profileListPresenter = ProfilePresenter.ProfileListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.instance.appComponent.inject(this)

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

        usersRepo.getRepos(user).observeOn(AndroidSchedulers.mainThread()).subscribe({ repos ->
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