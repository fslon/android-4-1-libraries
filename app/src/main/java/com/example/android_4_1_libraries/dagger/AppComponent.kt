package com.example.android_4_1_libraries.dagger

import com.example.android_4_1_libraries.presenter.MainPresenter
import com.example.android_4_1_libraries.presenter.ProfilePresenter
import com.example.android_4_1_libraries.presenter.RepositoryPresenter
import com.example.android_4_1_libraries.presenter.UsersPresenter
import com.example.android_4_1_libraries.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        CacheModule::class,
        ApiModule::class,
        RepoModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)

    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(repositoryPresenter: RepositoryPresenter)
    fun inject(profilePresenter: ProfilePresenter)

}
