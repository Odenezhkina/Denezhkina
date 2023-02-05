package com.study.presentation.recycler

import androidx.recyclerview.widget.RecyclerView
import com.study.domain.model.Movie
import com.study.presentation.mapper.toMovie
import com.study.presentation.model.UiMovie
import com.study.ui.databinding.ItemMovieBinding
import com.study.ui.loadImage

internal class MovieViewHolder(
    private val binding: ItemMovieBinding,
    private val onLongClick: ((Movie) -> Unit)? = null,
    private val onMovieClick: ((Int) -> Unit)? = null
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: UiMovie) {
        with(binding) {
            tvTitle.text = movie.title
            val genres: String = movie.genre
            tvDescription.text = root.context.getString(
                com.study.ui.R.string.rv_genre_with_year,
                genres,
                movie.year
            )
            movie.imageUrl?.let { ivMovieImage.loadImage(it) }
            root.setOnClickListener {
                onMovieClick?.invoke(movie.id)
            }
            root.setOnLongClickListener {
                onLongClick?.invoke(movie.toMovie())
                true
            }

        }
    }

}
