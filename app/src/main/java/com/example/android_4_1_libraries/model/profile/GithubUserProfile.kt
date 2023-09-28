package com.example.android_4_1_libraries.model.profile

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUserProfile(
    @Expose val id: String? = null,
    @Expose val name: String? = null,
    @Expose val forksCount: Int = 0
) : Parcelable
