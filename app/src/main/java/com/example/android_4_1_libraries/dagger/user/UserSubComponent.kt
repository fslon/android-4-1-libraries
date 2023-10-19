package com.example.android_4_1_libraries.dagger.user

import com.example.android_4_1_libraries.dagger.repository.RepositorySubComponent
import com.example.android_4_1_libraries.presenter.UsersPresenter
import com.example.android_4_1_libraries.ui.adapter.UsersRVAdapter
import dagger.Subcomponent


@UserScope
@Subcomponent(
    modules = [
        UserModule::class
    ]
)

interface UserSubComponent {
    fun repositorySubComponent(): RepositorySubComponent

    fun inject(usersPresenter: UsersPresenter)
    fun inject(usersRVAdapter: UsersRVAdapter)

}