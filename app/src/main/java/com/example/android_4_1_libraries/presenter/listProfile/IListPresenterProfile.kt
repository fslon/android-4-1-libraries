package com.example.android_4_1_libraries.presenter.listProfile

import com.example.android_4_1_libraries.view.listProfile.IItemViewProfile

interface IListPresenterProfile<V : IItemViewProfile> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}
