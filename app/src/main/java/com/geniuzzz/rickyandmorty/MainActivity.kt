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
//import okhttp3.OkHttpClient
//import sun.util.logging.LoggingSupport.setLevel



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://rickandmortyapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)

        api.getEpisodes().enqueue(object: Callback<Episode>{
            override fun onFailure(call: Call<Episode>, t: Throwable) {

            }

            override fun onResponse(call: Call<Episode>, response: Response<Episode>) {

                showData(response.body()!!.results)
                  }

        })

    }
    fun showData(result: List<Result>) {
        recycler_episodes.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = EpisodeAdapter(result)
        }
    }
}
