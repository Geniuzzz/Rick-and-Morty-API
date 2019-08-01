package com.geniuzzz.rickyandmorty

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/api/episode")
    fun getEpisodes() : Call<Episode>


}