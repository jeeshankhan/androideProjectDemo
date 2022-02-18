package com.example.apiapp

import android.content.Context
import android.content.UriMatcher
import android.media.Image
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso



class MyAdapter(val context: Context, val userList: List<MyDataModel>) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userId: TextView
        var title: TextView
        val imageviews:ImageView
        val btn: Button = itemView.findViewById(R.id.button)

        init {
            userId = itemView.findViewById(R.id.v1)
            title = itemView.findViewById(R.id.v2)
            imageviews = itemView.findViewById(R.id.imageview)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemview = LayoutInflater.from(context).inflate(R.layout.items_layout, parent, false)
        return ViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.userId.text = userList[position].url
        holder.title.text = userList[position].title
         // holder.imageviews.setImageURI(Uri.parse("http://api.learn2crack.com/android/images/marshmallow.png"))
         // Picasso.with(context).load(userList[position].url).resize(120, 60).into(holder.imageviews);
         Glide.with(context).load("http://api.learn2crack.com/android/images/marshmallow.png").into(holder.imageviews)


            holder.btn.setOnClickListener() {
            Toast.makeText(context, "You clicked me." +Uri.parse(userList[position].url), Toast.LENGTH_SHORT).show()
            Log.d("MainActivity", "jeeshan khan" +  userList[position].url)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}