package com.example.android_4_1_libraries.presenter

import com.example.android_4_1_libraries.model.CountersModel
import com.example.android_4_1_libraries.view.MainView

class MainPresenter(val view: MainView) {
    val model = CountersModel()

    fun counterClickButton1() {
        val nextValue = model.next(0)
        view.setButton1Text(nextValue.toString())
    }

    fun counterClickButton2() {
        val nextValue = model.next(1)
        view.setButton2Text(nextValue.toString())
    }

    fun counterClickButton3() {
        val nextValue = model.next(2)
        view.setButton3Text(nextValue.toString())
    }


}
