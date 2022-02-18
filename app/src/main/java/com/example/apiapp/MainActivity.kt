package com.example.apiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://jsonplaceholder.typicode.com"
class MainActivity : AppCompatActivity() {
     lateinit var layoutlinearmanager: LinearLayoutManager
     lateinit var  myAdapter:MyAdapter
     //lateinit var rclv:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         getMyData()
       val rclv:RecyclerView = findViewById(R.id.recyclerView)
        rclv.setHasFixedSize(true)
        layoutlinearmanager= LinearLayoutManager(this)
        rclv.layoutManager = layoutlinearmanager


//        var rf= Retrofit.Builder()
//            .baseUrl(RetrofitInterface.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create()).build()
//        var api = rf.create(RetrofitInterface::class.java)
//        var call = api.posts
//        call?.enqueue(object: Callback<List<Model?>?> {
//            override fun onResponse(call: Call<List<Model?>?>, response: Response<List<Model?>?>) {
//
//                var postlist:List<Model>?= response.body() as List<Model>
//                var post= arrayOfNulls<String>(postlist!!.size)
//
//                for (i in postlist!!.indices) {
//                    post[i] = postlist!![i]!!.title
//                    //post[i] = postlist!![i]!!.thumbnailUrl
//                }
//                var adapters = ArrayAdapter<String>(applicationContext,android.R.layout.simple_dropdown_item_1line,post)
//                val list = findViewById<ListView>(R.id.v1)
//                list.adapter = adapters
//            }
//          override fun onFailure(call: Call<List<Model?>?>, t: Throwable) {
//
//            }
//
//        })
    }


    private fun getMyData() {
        val retrofitbuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)
        val retrodata= retrofitbuilder.getData()

        retrodata.enqueue(object : Callback<List<MyDataModel>?> {
            override fun onResponse(
                call: Call<List<MyDataModel>?>,
                response: Response<List<MyDataModel>?>
            ) {
                val resrponsebody= response.body()!!
                myAdapter = MyAdapter(baseContext,resrponsebody)
                findViewById<RecyclerView>(R.id.recyclerView).adapter= myAdapter
            }

            override fun onFailure(call: Call<List<MyDataModel>?>, t: Throwable) {
            Log.d("MainActivity","onFailure" + t.message)
            }
        })
    }
}