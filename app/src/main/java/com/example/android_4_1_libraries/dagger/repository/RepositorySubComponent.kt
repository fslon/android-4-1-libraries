package com.example.android_4_1_libraries.dagger.repository

import com.example.android_4_1_libraries.presenter.ProfilePresenter
import com.example.android_4_1_libraries.presenter.RepositoryPresenter
import dagger.Subcomponent

@RepositoryScope
@Subcomponent(
    modules = [
        RepositoryModel::class
    ]
)

interface RepositorySubComponent {

    fun inject(profilePresenter: ProfilePresenter)
    fun inject(repositoryPresenter: RepositoryPresenter)


}