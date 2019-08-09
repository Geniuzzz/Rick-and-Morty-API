package com.geniuzzz.rickyandmorty

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.character_list.view.*

class CharacterAdapter(private val character: List<CharacterResult>?) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.character_list, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return character!!.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val character = character!![p1]

        val charSpecie = "Specie: " + character.species
        p0.characterSpecie.text = charSpecie
        val charGender = "Gender: " + character.gender
        p0.characterGender.text = charGender
        val charName = "Name: " + character.name
        p0.characterName.text = charName
        val charType = "Type: " + character.type
        p0.characterType.text = charType
        val charCreated = "Created: " + character.created
        p0.characterCreated.text = charCreated
        val charId = "Id: " + character.id
        p0.characterId.text = charId

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