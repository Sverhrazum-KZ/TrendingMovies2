package com.example.popularmoviesattempt2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmoviesattempt2.data.Result
import com.squareup.picasso.Picasso

class CustomAdapter(
    private val mList: List<Result>?,
    val mItemClickListener: ItemClickListener
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    interface ItemClickListener {
        fun onItemClick(position: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = mList?.get(position)
        Picasso.get().load("https://image.tmdb.org/t/p/w500/" + mList?.get(position)?.poster_path)
            .into(holder.imageView);
    }
    override fun getItemCount(): Int {
        return mList!!.size
    }
    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        init {
            ItemView.setOnClickListener {
                mList?.get(position)?.id?.let { it -> mItemClickListener.onItemClick(it) }
            }
        }
    }
}