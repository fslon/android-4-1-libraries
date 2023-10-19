package com.example.android_4_1_libraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_4_1_libraries.dagger.App
import com.example.android_4_1_libraries.databinding.FragmentRepositoryBinding
import com.example.android_4_1_libraries.model.profile.GithubUserProfile
import com.example.android_4_1_libraries.presenter.RepositoryPresenter
import com.example.android_4_1_libraries.ui.activity.BackButtonListener
import com.example.android_4_1_libraries.view.RepositoryView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepositoryFragment : MvpAppCompatFragment(), RepositoryView, BackButtonListener {
    private var _binding: FragmentRepositoryBinding? = null
    private val binding get() = _binding!!

    val presenter: RepositoryPresenter by moxyPresenter {
        val repository =
            arguments?.getParcelable<GithubUserProfile>(REPOSITORY_ARG) as GithubUserProfile
        RepositoryPresenter(repository).apply {
            App.instance.repositorySubComponent?.inject(this)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentRepositoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.loadData()

    }


    override fun setRepositoryName(name: String) {
        binding.tvRepositoryNameResult.text = name
    }

    override fun setForksCount(forksNumber: String) {
        binding.tvNumberOfForksResult.text = forksNumber
    }


    override fun backPressed() = presenter.backPressed()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val REPOSITORY_ARG = "repository"
        fun newInstance(repository: GithubUserProfile) =
            RepositoryFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(REPOSITORY_ARG, repository)
                }
            }
    }

}
