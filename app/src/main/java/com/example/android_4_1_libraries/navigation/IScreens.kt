package com.example.android_4_1_libraries.navigation

import com.example.android_4_1_libraries.model.GithubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun profileUser(user: GithubUser): Screen
}
