package com.example.fininassignment.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fininassignment.R
import com.example.fininassignment.data.model.User
import kotlinx.android.synthetic.main.list_item.view.*

class UserAdapter : PagedListAdapter<User, UserAdapter.UserViewHolder>(COMPARATOR) {

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView = view.user_avatar
        private val userName = view.name
        private val userEmail = view.email
        fun bind(user: User) {
            userName.text = user.firstName + " " + user.lastName
            userEmail.text = user.email
            Glide.with(imageView.context)
                .load(user.avatar)
                .placeholder(R.drawable.placeholder)
                .into(imageView);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        user?.let { holder.bind(it) }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
                newItem == oldItem
        }
    }
}