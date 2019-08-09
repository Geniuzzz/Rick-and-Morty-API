package com.geniuzzz.rickyandmorty

import android.os.Parcel
import android.os.Parcelable

class Episode(
    val info: Info,
    val results: List<Result>
)


class Result(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)

class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: String
)

class Characters(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)

