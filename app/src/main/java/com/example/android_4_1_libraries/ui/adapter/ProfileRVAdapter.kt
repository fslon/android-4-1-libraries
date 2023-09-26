package com.example.android_4_1_libraries.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.android_4_1_libraries.databinding.ItemUserProfileBinding
import com.example.android_4_1_libraries.presenter.listProfile.IUserListPresenterProfile
import com.example.android_4_1_libraries.view.glide.IImageLoader
import com.example.android_4_1_libraries.view.listProfile.UserItemViewProfile

class ProfileRVAdapter(
    val presenter: IUserListPresenterProfile, val imageLoader:
    IImageLoader<ImageView>
) : RecyclerView.Adapter<ProfileRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemUserProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    override fun getItemCount() = presenter.getCount()


    inner class ViewHolder(val vb: ItemUserProfileBinding) : RecyclerView.ViewHolder(vb.root), UserItemViewProfile {
        override var pos = -1

        override fun setRepositoryName(text: String) = with(vb) {
            tvRepositoryNameResult.text = text
        }


    }
}
