package com.elmaddinasger.internintelligencemovieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.elmaddinasger.internintelligencemovieapp.databinding.ItemMovieCardBinding
import com.elmaddinasger.internintelligencemovieapp.models.LocalMovieModel

class MovieCardAdapter: RecyclerView.Adapter<MovieCardAdapter.MovieCardViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<LocalMovieModel>() {
        override fun areItemsTheSame(oldItem: LocalMovieModel, newItem: LocalMovieModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LocalMovieModel, newItem: LocalMovieModel): Boolean {
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

    fun setMovieCardList (movieCardList: MutableList<LocalMovieModel>){
        asyncListDiffer.submitList(movieCardList)
    }

    inner class MovieCardViewHolder(private val binding: ItemMovieCardBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(currentMovieCard: LocalMovieModel){
            binding.apply {
                Glide.with(root.context)
                    .load(currentMovieCard.posterPath)
                    .into(imgMoviePhoto)

                txtvwMovieName.text = currentMovieCard.title

                txtvwMovieSize.text = currentMovieCard.id.toString()
                txtvwMovieGenre.text = currentMovieCard.title
                txtvwMovieTime.text = currentMovieCard.id.toString()
            }
        }
    }

}