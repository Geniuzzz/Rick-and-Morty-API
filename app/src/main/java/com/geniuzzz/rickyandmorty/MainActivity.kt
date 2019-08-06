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

    var receivedCharacters = mutableListOf<Characters>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://rickandmortyapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val apiService = retrofit.create(ApiService::class.java)

        ListCharacters.setOnClickListener {

            val intent = Intent (this, CharacterActivity:: class.java)
            startActivity(intent)
        }

        val request = apiService.getEpisodes()


        request.enqueue(object : Callback<Episode> {
            override fun onFailure(call: Call<Episode>, t: Throwable) {

            }

            override fun onResponse(call: Call<Episode>, response: Response<Episode>) {
                val resultList = response.body()!!.results
                showEpisodes(resultList)

                for (result in resultList) {

                    val urlList = result.characters
                    val listSize = urlList.size

                    for (urls in urlList) {
                        val urlNew = urls

                        // val cut = urlNew.substringAfter("https://rickandmortyapi.com/api/character/")
                        d("receive", "CutId: ${listSize}")


                        val api = retrofit.create(ApiService::class.java)



                        val req = api.getCharacters("${urlNew}")

                        req.enqueue(object : Callback<Characters> {
                            override fun onFailure(call: Call<Characters>, t: Throwable) {
                                d("characters", "onFailure")

                            }

                            override fun onResponse(
                                call: Call<Characters>, response: Response<Characters>
                            ) {

                                d("characters", "onResponse: ${response.body()!!.name}")

                                val characterList = response.body()!!

                                receivedCharacters.add(characterList)
                                showCharacters(receivedCharacters)
                            }

                        })


                    }
                }
            }
        })
    }

    fun showEpisodes(result: List<Result>) {
        recycler_episodes.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = EpisodeAdapter(result)
        }
    }
    fun showCharacters(characters: List<Characters>) {
        recycler_character.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayout.HORIZONTAL, false)
            adapter = CharacterAdapter2(characters)
        }
    }

    }
