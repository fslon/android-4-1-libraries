package com.example.android_4_1_libraries.dagger

import com.example.android_4_1_libraries.presenter.MainPresenter
import com.example.android_4_1_libraries.presenter.UsersPresenter
import com.example.android_4_1_libraries.ui.activity.MainActivity
import com.example.android_4_1_libraries.ui.fragment.ProfileFragment
import com.example.android_4_1_libraries.ui.fragment.RepositoryFragment
import com.example.android_4_1_libraries.ui.fragment.UsersFragment
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

    //При выполнении практического задания это должно отсюда уйти
    fun inject(userFragment: ProfileFragment)
    fun inject(repositoryFragment: RepositoryFragment)
    fun inject(usersFragment: UsersFragment)
}
