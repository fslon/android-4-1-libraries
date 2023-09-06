package com.example.android_4_1_libraries

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_4_1_libraries.adapter.UsersRVAdapter
import com.example.android_4_1_libraries.databinding.ActivityMainBinding
import com.example.android_4_1_libraries.model.CountersModel
import com.example.android_4_1_libraries.model.GithubUsersRepo
import com.example.android_4_1_libraries.presenter.MainPresenter
import com.example.android_4_1_libraries.view.MainView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private var vb: ActivityMainBinding? = null
    private var adapter: UsersRVAdapter? = null
    private val presenter by moxyPresenter { MainPresenter(GithubUsersRepo()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(this)
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        vb?.rvUsers?.adapter = adapter

    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }
}
