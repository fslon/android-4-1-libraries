package com.example.android_4_1_libraries.dagger

import com.example.android_4_1_libraries.dagger.user.UserSubComponent
import com.example.android_4_1_libraries.presenter.MainPresenter
import com.example.android_4_1_libraries.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        CacheModule::class,
        CiceroneModule::class,
        ImageModule::class
//        RepoModule::class
    ]
)
interface AppComponent {
    fun userSubcomponent(): UserSubComponent

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)

//    fun inject(usersPresenter: UsersPresenter)
//    fun inject(repositoryPresenter: RepositoryPresenter)
//    fun inject(profilePresenter: ProfilePresenter)

}
