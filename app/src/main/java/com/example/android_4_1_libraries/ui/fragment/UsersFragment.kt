package com.example.android_4_1_libraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_4_1_libraries.dagger.App
import com.example.android_4_1_libraries.databinding.FragmentUsersBinding
import com.example.android_4_1_libraries.model.room.Database
import com.example.android_4_1_libraries.presenter.UsersPresenter
import com.example.android_4_1_libraries.ui.activity.BackButtonListener
import com.example.android_4_1_libraries.ui.adapter.UsersRVAdapter
import com.example.android_4_1_libraries.view.UsersView
import com.example.android_4_1_libraries.view.glide.GlideImageLoader
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    private var vb: FragmentUsersBinding? = null


    @Inject
    lateinit var database: Database

    val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(AndroidSchedulers.mainThread()).apply {
            App.instance.appComponent.inject(this)
        }
    }

    var adapter: UsersRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentUsersBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        vb?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun release() {
        TODO("Not yet implemented")
    }

    override fun backPressed() = presenter.backPressed()

    companion object {
        fun newInstance() = UsersFragment().apply {
            App.instance.appComponent.inject(this)
        }
    }


}
