package com.example.popularmoviesattempt2.model.apis

import com.example.popularmoviesattempt2.data.MovieDetails
import com.example.popularmoviesattempt2.data.Movies
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("3/trending/movie/day")
    fun getMovies(@Query("api_key") sort: String): Call<Movies>

    @GET("3/movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movie_Id: Int, @Query("api_key") sort: String): Call<MovieDetails>
    companion object {
        var BASE_URL = "https://api.themoviedb.org/"

        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}