package com.example.android_4_1_libraries.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepositoryView : MvpView {
    fun setRepositoryName(name: String)
    fun setForksCount(forksNumber: String)
}