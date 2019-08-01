package com.geniuzzz.rickyandmorty

import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
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

  //      character.setOnClickListener {
//                recycler_characters.visibility = View.VISIBLE
//        }

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://rickandmortyapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)

        api.getEpisodes().enqueue(object: Callback<Episode>{
            override fun onFailure(call: Call<Episode>, t: Throwable) {

            }

            override fun onResponse(call: Call<Episode>, response: Response<Episode>) {

                showEpisodes(response.body()!!.results)

                  }

        })

    }
    fun showEpisodes(result: List<Result>) {
        recycler_episodes.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = EpisodeAdapter(result)
        }
    }
//    fun showCharacters(characters : List<Characters>) {
//        recycler_characters.apply {
//            layoutManager = LinearLayoutManager(this@MainActivity)
//            adapter = CharacterAdapter(characters)
//        }
//    }
}
