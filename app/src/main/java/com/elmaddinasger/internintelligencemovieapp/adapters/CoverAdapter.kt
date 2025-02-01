package com.elmaddinasger.internintelligencemovieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.elmaddinasger.internintelligencemovieapp.databinding.ItemCoverListBinding
import com.elmaddinasger.internintelligencemovieapp.models.CoverModel
import com.elmaddinasger.internintelligencemovieapp.models.MovieModel

class CoverAdapter(): RecyclerView.Adapter<CoverAdapter.CoverViewHolder>() {
    private val diffUtil = object : DiffUtil.ItemCallback<CoverModel>() {
        override fun areItemsTheSame(oldItem: CoverModel, newItem: CoverModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CoverModel, newItem: CoverModel): Boolean {
            return oldItem == newItem
        }
    }
    private val asyncListDiffer = AsyncListDiffer(this,diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoverViewHolder {
       val binding = ItemCoverListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CoverViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return asyncListDiffer.currentList.size
    }

    override fun onBindViewHolder(holder: CoverViewHolder, position: Int) {
        val currentCover = asyncListDiffer.currentList[position]
        holder.bind(currentCover)
    }
    fun setList(currentCoverList: MutableList<CoverModel>) {
    asyncListDiffer.submitList(currentCoverList)
    }
    inner class CoverViewHolder(private val binding: ItemCoverListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind (currentCover: CoverModel){
            binding.apply {
                txtvwSeeAll.text = "See all"
                txtvwCoverName.text = currentCover.coverName
                val coverMovieAdapter = CoverMovieAdapter()
                coverMovieAdapter.setMovieList(currentCover.movieList)
                rvMovies.adapter = coverMovieAdapter
            }
        }
    }

}