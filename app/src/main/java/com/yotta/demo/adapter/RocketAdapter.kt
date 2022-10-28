package com.yotta.demo.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.yotta.demo.R
import com.yotta.demo.activity.RocketDetails
import com.yotta.demo.databinding.RecyclerviewRocketsBinding
import com.yotta.demo.model.Rockets
import com.yotta.demo.model.RocketsItem

class RocketAdapter(private val rockets: List<Rockets>,var mContext: Context) :
    RecyclerView.Adapter<RocketAdapter.RocketViewHolder>() {
    class RocketViewHolder(val recyclerviewMovieBinding : RecyclerviewRocketsBinding) : RecyclerView.ViewHolder(recyclerviewMovieBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = RocketViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recyclerview_rockets,parent,false
        )
    )

    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        holder.recyclerviewMovieBinding.rockets = rockets[position]



        holder.itemView.setOnClickListener {
            val intent = Intent(mContext,RocketDetails::class.java);
            mContext.startActivity(intent)
        }
    }

    override fun getItemCount() = rockets.size
}