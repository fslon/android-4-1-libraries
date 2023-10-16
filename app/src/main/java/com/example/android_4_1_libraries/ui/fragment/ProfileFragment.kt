package com.example.android_4_1_libraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_4_1_libraries.dagger.App
import com.example.android_4_1_libraries.databinding.FragmentProfileBinding
import com.example.android_4_1_libraries.model.profile.ApiHolderProfile
import com.example.android_4_1_libraries.model.profile.RetrofitGithubUsersRepoProfile
import com.example.android_4_1_libraries.model.room.Database
import com.example.android_4_1_libraries.model.room.cache.RoomGithubRepositoriesCache
import com.example.android_4_1_libraries.model.users.GithubUser
import com.example.android_4_1_libraries.navigation.AndroidScreens
import com.example.android_4_1_libraries.presenter.ProfilePresenter
import com.example.android_4_1_libraries.ui.activity.BackButtonListener
import com.example.android_4_1_libraries.ui.adapter.ProfileRVAdapter
import com.example.android_4_1_libraries.ui.network.AndroidNetworkStatus
import com.example.android_4_1_libraries.view.ProfileView
import com.example.android_4_1_libraries.view.glide.GlideImageLoader
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject


class ProfileFragment() : MvpAppCompatFragment(), ProfileView, BackButtonListener {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
//    var thisUser = user


    @Inject
    lateinit var database: Database
    @Inject
    lateinit var router: Router


    val presenter: ProfilePresenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(USER_ARG) as GithubUser
        ProfilePresenter(
            AndroidSchedulers.mainThread(),
            RetrofitGithubUsersRepoProfile(
                ApiHolderProfile.api,
                AndroidNetworkStatus(App.instance), database, RoomGithubRepositoriesCache()
            ),
            router,
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
        private const val USER_ARG = "user"
        fun newInstance(user: GithubUser) = ProfileFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, user)
            }
            App.instance.appComponent.inject(this)
        }
    }

}