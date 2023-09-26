package com.example.android_4_1_libraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_4_1_libraries.App
import com.example.android_4_1_libraries.databinding.FragmentProfileBinding
import com.example.android_4_1_libraries.model.GithubUser
import com.example.android_4_1_libraries.model.profile.ApiHolderProfile
import com.example.android_4_1_libraries.model.profile.RetrofitGithubUsersRepoProfile
import com.example.android_4_1_libraries.navigation.AndroidScreens
import com.example.android_4_1_libraries.presenter.ProfilePresenter
import com.example.android_4_1_libraries.ui.activity.BackButtonListener
import com.example.android_4_1_libraries.ui.adapter.ProfileRVAdapter
import com.example.android_4_1_libraries.view.ProfileView
import com.example.android_4_1_libraries.view.glide.GlideImageLoader
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class ProfileFragment(user: GithubUser) : MvpAppCompatFragment(), ProfileView, BackButtonListener {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
//    var thisUser = user

    val presenter: ProfilePresenter by moxyPresenter {
        ProfilePresenter(
            AndroidSchedulers.mainThread(),
            RetrofitGithubUsersRepoProfile(ApiHolderProfile.api, user.reposUrl.toString()),
            App.instance.router,
            AndroidScreens(),
            user
        )
    }

    var adapter: ProfileRVAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        if (arguments?.getParcelable<GithubUser>("user") != null) thisUser = arguments?.getParcelable("user")!!
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun setUserLogin(userName: String) {
        binding.textview.text = userName
    }

    override fun init() {
        binding.rvUsers.layoutManager = LinearLayoutManager(context)
        adapter = ProfileRVAdapter(presenter.profileListPresenter, GlideImageLoader())
        binding.rvUsers.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()


    companion object {
        fun newInstance(user: GithubUser) = ProfileFragment(user)
    }

}