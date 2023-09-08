package com.example.android_4_1_libraries.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_4_1_libraries.App
import com.example.android_4_1_libraries.ui.adapter.UsersRVAdapter
import com.example.android_4_1_libraries.databinding.FragmentUsersBinding
import com.example.android_4_1_libraries.model.GithubUsersRepo
import com.example.android_4_1_libraries.navigation.AndroidScreens
import com.example.android_4_1_libraries.presenter.UsersPresenter
import com.example.android_4_1_libraries.ui.activity.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    private var vb: FragmentUsersBinding? = null

    companion object {
        fun newInstance() = UsersFragment()
    }

    val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(GithubUsersRepo(), App.instance.router, AndroidScreens()) }

    var adapter: UsersRVAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) =
        FragmentUsersBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }
    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        vb?.rvUsers?.adapter = adapter
    }
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun release() {
        TODO("Not yet implemented")
    }

    override fun backPressed() = presenter.backPressed()
}
