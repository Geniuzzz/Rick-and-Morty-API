package com.geniuzzz.rickyandmorty

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log.d
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_character.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharacterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)
        supportActionBar?.title = "Rick and Morty CharacterResult"

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://rickandmortyapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)

        fun makeRequest() {
            var gender = ""
            when (spinner_gender.selectedItem) {
                "Male" -> gender = "male"
                "Female" -> gender = "female"
                "All" -> gender = ""
            }

            api.getCharacterList(gender).enqueue(object : Callback<Character> {
                override fun onFailure(call: Call<Character>, t: Throwable) {

                }

                override fun onResponse(call: Call<Character>, response: Response<Character>) {

                    d("char", "response ${response.body()!!}")
                    showCharacters(response.body()!!.results)
                }
            })
        }
        makeRequest()

        button.setOnClickListener {
            makeRequest()
        }
        textView4.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun showCharacters(character: List<CharacterResult>) {
        recycler_characters.apply {
            layoutManager = LinearLayoutManager(this@CharacterActivity, LinearLayout.HORIZONTAL, false)
            adapter = CharacterAdapter(character)
        }
        val genderAdapter =
            ArrayAdapter.createFromResource(this, R.array.Gender, android.R.layout.simple_spinner_item)
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_gender.adapter = genderAdapter
    }
}