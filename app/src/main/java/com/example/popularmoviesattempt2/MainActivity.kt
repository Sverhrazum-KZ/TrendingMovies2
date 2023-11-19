package com.example.popularmoviesattempt2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("testlogs", "RegistrationActivity start registration")

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<ItemsViewModel>()

        for (i in 1..20) {
            //До этого была такая строка data.add(ItemsViewModel(R.drawable.common_full_open_on_phone, "Item " + i))
            data.add(
                ItemsViewModel(
                    com.google.android.gms.base.R.drawable.common_full_open_on_phone,
                    "Item " + i
                )
            )
        }

        val adapter = CustomAdapter(data)

        recyclerView.adapter = adapter


        val apiInterface = ApiInterface.create().getMovies("6e9ffacfbd27705fa7588e5288d2837c")

        //apiInterface.enqueue( Callback<List<Movie>>())
        apiInterface.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>?, response: Response<Movies>?) {
                Log.d("testLogs", "OnResponse Success ${response?.body()?.results}")
                /*if(response?.body() != null)
                    recyclerAdapter.setMovieListItems(response.body()!!)*/
            }

            override fun onFailure(call: Call<Movies>?, t: Throwable?) {
                Log.d("testLogs", "onFailure : ${t?.message}")
            }
        })
    }
}