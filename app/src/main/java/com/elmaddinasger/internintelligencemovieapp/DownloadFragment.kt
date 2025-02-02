package com.elmaddinasger.internintelligencemovieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elmaddinasger.internintelligencemovieapp.adapters.MovieCardAdapter
import com.elmaddinasger.internintelligencemovieapp.databinding.FragmentDownloadBinding
import com.elmaddinasger.internintelligencemovieapp.models.MovieModel

class DownloadFragment : Fragment() {
    private lateinit var binding: FragmentDownloadBinding
    private lateinit var movieCardAdapter: MovieCardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDownloadBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMovieCardAdapter(images)
    }
    private val images = mutableListOf(
        MovieModel(1,"apple","https://fastly.picsum.photos/id/866/200/300.jpg?hmac=rcadCENKh4rD6MAp6V_ma-AyWv641M4iiOpe1RyFHeI"),
        MovieModel(2,"apple","https://picsum.photos/seed/picsum/200/300"),
        MovieModel(3,"apple","https://picsum.photos/200/300?grayscale"),
        MovieModel(4,"apple","https://picsum.photos/id/237/200/300"),
        MovieModel(5,"apple","https://picsum.photos/seed/picsum/200/300"),
        MovieModel(6,"apple","https://picsum.photos/200/300?grayscale")
    )


    private fun getMovieCardAdapter (movieCardList: MutableList<MovieModel>) {
        movieCardAdapter = MovieCardAdapter()
        movieCardAdapter.setMovieCardList(movieCardList)
        binding.rvDownloadList.adapter = movieCardAdapter
    }


}