package com.study.presentation.recycler

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.study.domain.Movie

class MovieItemCallback : ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem.title == newItem.title
                && oldItem.description == newItem.description
                && oldItem.imageUrl == newItem.imageUrl
}
