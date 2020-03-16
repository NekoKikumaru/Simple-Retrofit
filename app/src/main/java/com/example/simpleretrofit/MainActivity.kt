package com.example.simpleretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpleretrofit.adapter.PostAdapter
import com.example.simpleretrofit.api.PostApiInterface
import com.example.simpleretrofit.model.Post
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getPostTitle()
    }

    fun loadPostTitle(postList: List<Post>) {
        var postAdapter = PostAdapter(postList)
        recycler_post.layoutManager = LinearLayoutManager(this)
        recycler_post.adapter = postAdapter
    }

    fun getPostTitle() {

        var BASE_URL = "https://jsonplaceholder.typicode.com/"
        var retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        var retroService = retrofit.create(PostApiInterface::class.java)

        var apiCall = retroService.getPost()

        apiCall.enqueue(object : Callback<List<Post>> {

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.d("Failure Connection >>>>", t.toString())
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                var postList = response.body()
                postList?.let { loadPostTitle(it) }
            }
        })
    }
}
