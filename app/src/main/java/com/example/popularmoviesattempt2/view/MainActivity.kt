package com.example.popularmoviesattempt2.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmoviesattempt2.CustomAdapter
import com.example.popularmoviesattempt2.MoviesDetailsActivity
import com.example.popularmoviesattempt2.R
import com.example.popularmoviesattempt2.data.ItemsViewModel
import com.example.popularmoviesattempt2.data.Movies
import com.example.popularmoviesattempt2.model.apis.ApiInterface
import com.example.popularmoviesattempt2.viewmodel.MoviesViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel: MoviesViewModel
    private lateinit var mMoviesRecycler:RecyclerView
    private lateinit var mMoviesAdapter: CustomAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("testlogs", "RegistrationActivity start registration")

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this,2)
        val data = ArrayList<ItemsViewModel>()
        mViewModel=MoviesViewModel()
        val result = mViewModel.getMovies()
        val apiInterface = ApiInterface.create().getMovies("c2c607cbe47210dc3704d1598c983e43")

        apiInterface.enqueue(object : Callback<Movies>, CustomAdapter.ItemClickListener {
            override fun onResponse(call: Call<Movies>?, response: Response<Movies>?) {
                Log.d("testLogs", "onResponse Success ${response?.body()?.results}")

                val adapter = CustomAdapter(response?.body()?.results,this)
                recyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<Movies>?, t: Throwable?) {
                Log.d("testLogs", "onResponse Failure: ${t?.message}")
            }

            override fun onItemClick(id:Int) {
                val intent = Intent(this@MainActivity, MoviesDetailsActivity::class.java)
                intent.putExtra("id",id)
                startActivity(intent)
            }
        })

    }
}

