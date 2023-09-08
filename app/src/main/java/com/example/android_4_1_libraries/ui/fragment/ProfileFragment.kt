package com.example.android_4_1_libraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android_4_1_libraries.App
import com.example.android_4_1_libraries.databinding.FragmentProfileBinding
import com.example.android_4_1_libraries.model.GithubUsersRepo
import com.example.android_4_1_libraries.navigation.AndroidScreens
import com.example.android_4_1_libraries.presenter.ProfilePresenter
import com.example.android_4_1_libraries.presenter.UsersPresenter
import com.example.android_4_1_libraries.ui.activity.BackButtonListener
import com.example.android_4_1_libraries.view.ProfileView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class ProfileFragment : MvpAppCompatFragment(), ProfileView, BackButtonListener {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    val presenter: ProfilePresenter by moxyPresenter {
        ProfilePresenter(App.instance.router)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun setUser(userName: String) {
        binding.textview.text = userName
    }

    override fun backPressed() = presenter.backPressed()


    companion object {
        fun newInstance() = ProfileFragment()
    }

}