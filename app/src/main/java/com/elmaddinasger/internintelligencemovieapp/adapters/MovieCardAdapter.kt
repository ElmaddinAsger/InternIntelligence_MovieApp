package com.elmaddinasger.internintelligencemovieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.elmaddinasger.internintelligencemovieapp.databinding.ItemMovieCardBinding
import com.elmaddinasger.internintelligencemovieapp.models.MovieModel

class MovieCardAdapter: RecyclerView.Adapter<MovieCardAdapter.MovieCardViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<MovieModel>() {
        override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem == newItem
        }

    }

    private val asyncListDiffer = AsyncListDiffer(this,diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCardViewHolder {
        val binding = ItemMovieCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieCardViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return asyncListDiffer.currentList.size
    }

    override fun onBindViewHolder(holder: MovieCardViewHolder, position: Int) {
        val currentMovieCard = asyncListDiffer.currentList[position]
        holder.bind(currentMovieCard)

    }

    fun setMovieCardList (movieCardList: MutableList<MovieModel>){
        asyncListDiffer.submitList(movieCardList)
    }

    inner class MovieCardViewHolder(private val binding: ItemMovieCardBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(currentMovieCard: MovieModel){
            binding.apply {
                Glide.with(root.context)
                    .load(currentMovieCard.imageUrl)
                    .into(imgMoviePhoto)

                txtvwMovieName.text = currentMovieCard.movieName

                txtvwMovieSize.text = currentMovieCard.id.toString()
                txtvwMovieGenre.text = currentMovieCard.movieName
                txtvwMovieTime.text = currentMovieCard.id.toString()
            }
        }
    }

}