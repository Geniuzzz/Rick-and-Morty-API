package com.geniuzzz.rickyandmorty

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_character_detail.*
import kotlinx.android.synthetic.main.character_list.view.*
import kotlinx.android.synthetic.main.episodes_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharacterDetailActivity : AppCompatActivity() {

    var receivedCharacters = mutableListOf<Characters>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)


        val episodeId = intent.getIntExtra("EPISODE_ID", 1)
        val episodeTitle = intent.getStringExtra("EPISODE_TITLE")

        supportActionBar?.title = episodeTitle

        val retrofit = Retrofit.Builder()
            .baseUrl("http://rickandmortyapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(ApiService::class.java)

        apiService.getEpisodeDetails(episodeId).enqueue(object : Callback<Result> {
            override fun onFailure(call: Call<Result>, t: Throwable) {

            }

            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                d("new activity", "${response.body()}")
                var details = response.body()!!

                nameOfEpisode.text = details.episode + " (${details.name})"
                episodeAiring.text = "Airdate: " + details.air_date
                IdOfEpisode.text = "Id: " + details.id
                textView5.text = "Characters in this episode"

                val characterList = response.body()!!.characters

                for (url in characterList) {
                    apiService.getCharacterDetails(url).enqueue(object : Callback<Characters> {
                        override fun onFailure(call: Call<Characters>, t: Throwable) {

                        }

                        override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                            receivedCharacters.add(response.body()!!)
                            showCharacters(receivedCharacters)

                        }

                    })
                }
            }
        })


    }

    fun showCharacters(characters: List<Characters>) {
        recyclerView_episode2.apply {
            layoutManager = LinearLayoutManager(this@CharacterDetailActivity, LinearLayout.HORIZONTAL, false)
            adapter = CharacterDetailAdapter(characters)
        }


    }

    private class CharacterDetailAdapter(val character: List<Characters>) :
        RecyclerView.Adapter<CharacterDetailAdapter.ViewHolder>() {
        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CharacterDetailAdapter.ViewHolder {
            val v = LayoutInflater.from(p0.context).inflate(R.layout.character_list, p0, false)
            return ViewHolder(v)
        }

        override fun getItemCount(): Int {
            return character.size
        }

        override fun onBindViewHolder(p0: CharacterDetailAdapter.ViewHolder, p1: Int) {
            val character = character[p1]

            p0.characterSpecie.text = "Specie: " + character.species
            p0.characterGender.text = "Gender: " + character.gender
            p0.characterName.text = "Name: " + character.name
            p0.characterType.text = "Type: " + character.type
            p0.characterCreated.text = "Created: " + character.created
            p0.characterId.text = "Id: " + character.id

            val thumbnailCharImage = p0.itemView.characterImage
            Picasso.with(p0.itemView.context).load(character.image).into(thumbnailCharImage)
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val characterSpecie: TextView = itemView.characterSpecie
            val characterName: TextView = itemView.characterName
            val characterType: TextView = itemView.characterType
            val characterCreated: TextView = itemView.characterCreated
            val characterGender: TextView = itemView.characterGender
            val characterId: TextView = itemView.characterId
        }
    }
}