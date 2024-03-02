package com.mvi.mviplayapi.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mvi.mviplayapi.R
import com.mvi.mviplayapi.data.model.Game

class MainAdapter(
    private val games: ArrayList<Game>
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var gameName: TextView = itemView.findViewById(R.id.textViewGame)
        private var gameImage: ImageView = itemView.findViewById(R.id.imageViewAvatar)
        fun bind(game: Game) {
            gameName.text = game.name
            Glide.with(gameImage.context)
                .load(game.background_image)
                .into(gameImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row, parent,
                false
            )
        )

    override fun getItemCount(): Int = games.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(games[position])

    fun addData(list: List<Game>) {
        games.addAll(list)
    }

}