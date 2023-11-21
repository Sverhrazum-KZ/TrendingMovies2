package com.example.popularmoviesattempt2

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("3/trending/movie/day")
    fun getMovies(@Query("api_key") apiKey: String): Call<Movies>

    companion object {
        var BASE_URL = "https://api.themoviedb.org/"
        //var API_KEY = "c2c607cbe47210dc3704d1598c983e43" // Ваш API ключ

        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}