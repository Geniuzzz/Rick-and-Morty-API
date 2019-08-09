package com.geniuzzz.rickyandmorty

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.episodes_list.view.*


class EpisodeAdapter(val context: Context, val result: List<Result>?) : RecyclerView.Adapter<EpisodeAdapter.ViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.episodes_list, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return result!!.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val results = result!![p1]
        Log.d("YES", "onResponse: ${results.characters}")
        val id = results.id
        val episodeTitle = results.name + " (${results.episode})"

        p0.name.setOnClickListener {

            val intent = Intent(context, CharacterDetailActivity::class.java)
            intent.putExtra("EPISODE_ID", id)
            intent.putExtra("EPISODE_TITLE", episodeTitle)

            context.startActivity(intent)

        }

        p0.name.text = "Name: ${results.name}"
        p0.created.text = "Created: ${results.created}"
        p0.airDate.text = "Airdate: ${results.air_date}"
        p0.id.text = "Id: ${results.id}"
        p0.episode.text = "Episode: ${results.episode}"



    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.name
        val created: TextView = itemView.created
        val id: TextView = itemView.episodeId
        val airDate: TextView = itemView.airDate
        val episode: TextView = itemView.episode


    }
}