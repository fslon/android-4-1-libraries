package com.example.android_4_1_libraries.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

//@AddToEndSingle - есть ещё такой алиас

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView {
    fun init()
    fun updateList()
}
