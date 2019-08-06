package com.geniuzzz.rickyandmorty

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    @GET("/api/episode")
    fun getEpisodes() : Call<Episode>

    @GET
    fun getCharacters(@Url anotherUrl: String): Call <Characters>

    @GET("/api/character/")
    fun getCharacterList(@Query("gender") gender: String?): Call <Character>


}