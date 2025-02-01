package com.elmaddinasger.internintelligencemovieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elmaddinasger.internintelligencemovieapp.R
import com.elmaddinasger.internintelligencemovieapp.databinding.ItemCategoryBinding

class CategoryAdapter(private val categories: List<String>): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return categories.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.binding.btnCategory.text = categories[position]
        holder.bind()
    }

    inner class CategoryViewHolder (val binding: ItemCategoryBinding): RecyclerView.ViewHolder(binding.root){
        init {
            binding.btnCategory.setOnClickListener {
                val previousSelectedPosition = selectedPosition
                selectedPosition = bindingAdapterPosition
                notifyItemChanged(previousSelectedPosition)
                notifyItemChanged(selectedPosition)
            }
        }



        fun bind() {
            if (selectedPosition == bindingAdapterPosition) {
                binding.btnCategory.setBackgroundResource(R.drawable.active_button)
            } else {
                binding.btnCategory.setBackgroundResource(R.drawable.inactive_button)
            }
        }
    }
}