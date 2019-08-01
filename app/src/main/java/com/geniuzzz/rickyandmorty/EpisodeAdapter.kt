package com.geniuzzz.rickyandmorty

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import kotlinx.android.synthetic.main.episodes_list.view.*
import java.lang.reflect.Array


class EpisodeAdapter(val episode: List<Result>?):RecyclerView.Adapter<EpisodeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.episodes_list, p0,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return episode!!.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val episode = episode!![p1]
         Log.d("YES", "onResponse: ${episode.name}")

        p0.name.text = "Name: ${episode.name}"
        p0.created.text = "Created: ${episode.created}"
        p0.airDate.text = "Airdate: ${episode.air_date}"
        p0.id.text = "Id: ${episode.id}"
        p0.episode.text = "Episode: ${episode.episode}"


    }

    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.name
        val created: TextView = itemView.created
        val id: TextView = itemView.episodeId
        val airDate : TextView = itemView.airDate
        val episode: TextView = itemView.episode


}
}