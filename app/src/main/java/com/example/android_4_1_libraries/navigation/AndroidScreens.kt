package com.example.android_4_1_libraries.navigation

import com.example.android_4_1_libraries.view.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
}
