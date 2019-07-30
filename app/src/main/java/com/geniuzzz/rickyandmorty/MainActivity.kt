package com.geniuzzz.rickyandmorty

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
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


        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://rickandmortyapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)

        api.getEpisodes().enqueue(object : Callback<List<Episode>>{
            override fun onFailure(call: Call<List<Episode>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Episode>>, response: Response<List<Episode>>) {
                showData(response.body()!!)
            }

        })
    }
    fun showData(episode : List<Episode>) {
        recycler_episodes.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = EpisodeAdapter(episode)
            recycler_episodes.adapter = adapter
        }
    }
}
