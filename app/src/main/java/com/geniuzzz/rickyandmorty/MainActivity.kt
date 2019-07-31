package com.geniuzzz.rickyandmorty

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val interceptor = HttpLoggingInterceptor()
        val client: OkHttpClient =
            OkHttpClient.Builder().addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)).build()


        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://rickandmortyapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        val api = retrofit.create(ApiService::class.java)

        val call = api.getEpisodes()

        Log.i(" Login url", call.request().url().toString())

        call.enqueue(object : Callback<Episode> {

            override fun onResponse(call: Call<Episode>, response: Response<Episode>) {


                Log.d("episode", "onResponse " + response.raw().request().url())
                showData(response.body()?.results)

            }

            override fun onFailure(call: Call<Episode>, t: Throwable) {
                Log.d("episode", "onFailure: " + t.message)

            }
        })


    }

    fun showData(result: List<Result>?) {
        recycler_episodes.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = EpisodeAdapter(result)
        }
    }
}
