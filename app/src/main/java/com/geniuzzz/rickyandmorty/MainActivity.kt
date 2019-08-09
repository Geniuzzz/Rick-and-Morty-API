package com.geniuzzz.rickyandmorty

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log.d
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.episodes_list.*
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


        val apiService = retrofit.create(ApiService::class.java)

        ListCharacters.setOnClickListener {

            val intent = Intent(this, CharacterActivity::class.java)
            startActivity(intent)
        }

        val request = apiService.getEpisodes()


        request.enqueue(object : Callback<Episode> {
            override fun onFailure(call: Call<Episode>, t: Throwable) {

            }

            override fun onResponse(call: Call<Episode>, response: Response<Episode>) {
                val resultList = response.body()!!.results
                showEpisodes(resultList)

            }
        })
    }

    fun showEpisodes(result: List<Result>) {
        recycler_episodes.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = EpisodeAdapter(context, result)
        }
    }
}
