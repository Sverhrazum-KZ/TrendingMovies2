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
            data.add(ItemsViewModel(com.google.android.gms.base.R.drawable.common_full_open_on_phone,"Item " + i))
        }

        val apiInterface = ApiInterface.create().getMovies("c2c607cbe47210dc3704d1598c983e43")

        //apiInterface.enqueue( Callback<List<Movie>>())
        apiInterface.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>?,response: Response<Movies>?) {
                Log.d("testLogs", "onResponse Success ${response?.body()?.results}")
                /*if(response?.body() != null)
                    recyclerAdapter.setMovieListItems(response.body()!!)*/

                val adapter = CustomAdapter(response?.body()?.results)

                recyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<Movies>?, t: Throwable?) {
                Log.d("testLogs", "onResponse Failure: ${t?.message}")

            }
        })

    }//)
}//}
