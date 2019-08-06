package com.geniuzzz.rickyandmorty

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.character_list.view.*

class CharacterAdapter2(val character: List<Characters>?) : RecyclerView.Adapter< CharacterAdapter2.ViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.character_list, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return character!!.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {


        val character = character!![p1]

        p0.characterSpecie.text = "Specie:" + character.species
        p0.characterGender.text = "Gender:" + character.gender
        p0.characterName.text = "Name:" + character.name
        p0.characterType.text = "Type:" + character.type
        p0.characterCreated.text = "Created:" + character.created

        val thumbnailCharImage = p0.itemView.characterImage
        Picasso.with(p0.itemView.context).load(character.image).into(thumbnailCharImage)

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val characterSpecie: TextView = itemView.characterSpecie
        val characterName: TextView = itemView.characterName
        val characterType: TextView = itemView.characterType
        val characterCreated: TextView = itemView.characterCreated
        val characterGender: TextView = itemView.characterGender


    }
}
