package com.elmaddinasger.internintelligencemovieapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elmaddinasger.internintelligencemovieapp.adapters.MovieCardAdapter
import com.elmaddinasger.internintelligencemovieapp.databinding.FragmentDownloadBinding
import com.elmaddinasger.internintelligencemovieapp.models.LocalMovieModel
import com.elmaddinasger.internintelligencemovieapp.models.OnlineMovieList
import com.elmaddinasger.internintelligencemovieapp.services.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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

    private val images = mutableListOf<LocalMovieModel>()


    private fun getMovieCardAdapter (movieCardList: MutableList<LocalMovieModel>) {
        movieCardAdapter = MovieCardAdapter()
        movieCardAdapter.setMovieCardList(movieCardList)
        binding.rvDownloadList.adapter = movieCardAdapter
    }




}