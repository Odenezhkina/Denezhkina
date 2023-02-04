package com.study.data.repository

import com.study.common.Movie
import com.study.data.api.MoviesApi
import com.study.data.mappers.toDetailedMovie
import com.study.data.mappers.toMovies
import com.study.domain.model.DetailedMovie
import com.study.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MoviesApi
) : MovieRepository {

    override suspend fun getTopMovies(): List<Movie> {
        return api.getTopMovies().films.toMovies()
    }

    override suspend fun getMovieById(id: Int): DetailedMovie {
        return api.getMovieById(id).toDetailedMovie()
    }
}
