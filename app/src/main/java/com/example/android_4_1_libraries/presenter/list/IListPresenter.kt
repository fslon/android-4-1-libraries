package com.example.android_4_1_libraries.presenter.list

import com.example.android_4_1_libraries.view.list.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}
