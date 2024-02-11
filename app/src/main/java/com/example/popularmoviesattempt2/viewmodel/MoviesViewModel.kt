package com.example.popularmoviesattempt2.viewmodel

import com.example.popularmoviesattempt2.model.repository.MoviesDBRepository
import com.example.popularmoviesattempt2.model.repository.MoviesDBRepositoryImpl

class MoviesViewModel {
    private val mMoviesRepository: MoviesDBRepository = MoviesDBRepositoryImpl()

    fun getMovies(): String {
        return mMoviesRepository.getMovies()
    }

}