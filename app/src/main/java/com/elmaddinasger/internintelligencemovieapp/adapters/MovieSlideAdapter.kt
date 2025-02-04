package com.elmaddinasger.internintelligencemovieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.elmaddinasger.internintelligencemovieapp.databinding.ItemMovieSlideBinding
import com.elmaddinasger.internintelligencemovieapp.models.LocalMovieModel

class MovieSlideAdapter(private val movieList: List<LocalMovieModel>): RecyclerView.Adapter<MovieSlideAdapter.MovieSlideViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSlideViewHolder {
        val binding = ItemMovieSlideBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieSlideViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieSlideViewHolder, position: Int) {
       val currentMovie = movieList[position]
        holder.bind(currentMovie)
    }

    inner class MovieSlideViewHolder(private val binding: ItemMovieSlideBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(currentMovie: LocalMovieModel){
            Glide.with(binding.root.context)
                .load(currentMovie.posterPath)
                .into(binding.imgCurrentMovie)
        }
    }
}
