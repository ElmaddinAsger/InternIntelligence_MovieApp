package com.elmaddinasger.internintelligencemovieapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.elmaddinasger.internintelligencemovieapp.adapters.CategoryAdapter
import com.elmaddinasger.internintelligencemovieapp.adapters.CoverAdapter
import com.elmaddinasger.internintelligencemovieapp.adapters.MovieSlideAdapter
import com.elmaddinasger.internintelligencemovieapp.databinding.FragmentHomeBinding
import com.elmaddinasger.internintelligencemovieapp.models.CoverModel
import com.elmaddinasger.internintelligencemovieapp.models.Genre
import com.elmaddinasger.internintelligencemovieapp.models.Genres
import com.elmaddinasger.internintelligencemovieapp.models.LocalMovieModel
import com.elmaddinasger.internintelligencemovieapp.models.OnlineMovieList
import com.elmaddinasger.internintelligencemovieapp.services.Retrofit
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var movieSlideAdapter: MovieSlideAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var coverAdapter: CoverAdapter
    private lateinit var viewPager: ViewPager2

    private var currentPosition = 0
    private val token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0YjhhZGM4ZDA0YTA4ZTk4NjVlZDk2YjllOTc0ODYwYiIsIm5iZiI6MTczODM5MTMxMi42NzIsInN1YiI6IjY3OWRiZjEwMTc0MmI0NGExNGNiMzdmYiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.OzKXsqf73IJ5E6nPx4vbRe4iVCXOEn_cSFlheccQkbE"



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewPager(images)
        getMovieGenreList()
        getCategories(categories)
        startAutoMovieChange()
        changeTab()
        CoroutineScope(Dispatchers.IO).launch {
            getNowPlayingMovies()
            getTopRatedMovies()
            getPopularMovies()
            getUpcomingMovies()
            withContext(Dispatchers.Main){
                getCovers(coverList)
            }
        }



    }

    private fun retrofit () {


    }



    private val categories = mutableListOf<Genre>()

    private val images = mutableListOf(
        LocalMovieModel(1,"apple","https://fastly.picsum.photos/id/866/200/300.jpg?hmac=rcadCENKh4rD6MAp6V_ma-AyWv641M4iiOpe1RyFHeI"),
        LocalMovieModel(2,"apple","https://picsum.photos/seed/picsum/200/300"),
        LocalMovieModel(3,"apple","https://picsum.photos/200/300?grayscale"),
        LocalMovieModel(4,"apple","https://picsum.photos/id/237/200/300"),
        LocalMovieModel(5,"apple","https://picsum.photos/seed/picsum/200/300"),
        LocalMovieModel(6,"apple","https://picsum.photos/200/300?grayscale"))

    private val coverList = mutableListOf<CoverModel>()

    private fun getViewPager (movieList: List<LocalMovieModel>){
        movieSlideAdapter = MovieSlideAdapter(movieList)
        viewPager = binding.vpHeaderMovieSlide
        binding.vpHeaderMovieSlide.adapter = movieSlideAdapter
    }

    private fun getCategories (categories: List<Genre>) {
        categoryAdapter = CategoryAdapter(categories)
        binding.rvCategories.adapter = categoryAdapter
    }

    private fun startAutoMovieChange(){
        val handler = Handler(Looper.getMainLooper())

        val runnable = object : Runnable {
            override fun run() {
                if (currentPosition < movieSlideAdapter.itemCount - 1) {
                    currentPosition++

                } else {
                    currentPosition = 0
                }
                viewPager.setCurrentItem(currentPosition, true)
                changeTab()
                handler.postDelayed(this, 5000)
            }
        }



        handler.postDelayed(runnable, 5000)
    }

    private fun changeTab () {
        val tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->

            if (position == viewPager.currentItem) {
                tab.setIcon(R.drawable.active_dot)
            } else {
                tab.setIcon(R.drawable.inactive_dot)
            }
        }.attach()
    }

    private fun getNowPlayingMovies () {
        val call = Retrofit.movieApi.getNowPlayingMovies(token)
        call.enqueue(object : Callback<OnlineMovieList> {
            override fun onResponse(call: Call<OnlineMovieList>, response: Response<OnlineMovieList>) {

                if (response.isSuccessful) {
                    response.body()?.let { movieList ->

                        val upcomingMovies = mutableListOf<LocalMovieModel>()

                        movieList.results.forEach {
                            upcomingMovies.add(
                                LocalMovieModel(it.id.toLong(),it.original_title,"https://image.tmdb.org/t/p/w154${it.poster_path}")
                            )
                        }
                        val nowPlayingCover = CoverModel(1,"Now Playing",upcomingMovies)
                        if(!coverList.contains(nowPlayingCover)) {
                            coverList.add(nowPlayingCover)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<OnlineMovieList>, t: Throwable) {
                Log.e("RETROFIT","Network error: ${t.message}")
            }
        })

    }

    private fun getTopRatedMovies () {
        val call = Retrofit.movieApi.getTopRatedMovies(token)
        call.enqueue(object : Callback<OnlineMovieList> {
            override fun onResponse(call: Call<OnlineMovieList>, response: Response<OnlineMovieList>) {

                if (response.isSuccessful) {
                    response.body()?.let { movieList ->

                        val topRatedMovies = mutableListOf<LocalMovieModel>()

                        movieList.results.forEach {
                            topRatedMovies.add(
                                LocalMovieModel(it.id.toLong(),it.original_title,"https://image.tmdb.org/t/p/w154${it.poster_path}")
                            )
                        }
                        val topRatedCover = CoverModel(2,"Top Rated",topRatedMovies)
                        if(!coverList.contains(topRatedCover)) {
                            coverList.add(topRatedCover)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<OnlineMovieList>, t: Throwable) {
                Log.e("RETROFIT","Network error: ${t.message}")
            }
        })

    }

    private fun getPopularMovies () {
        val call = Retrofit.movieApi.getPopularMovies(token)
        call.enqueue(object : Callback<OnlineMovieList>{
            override fun onResponse(p0: Call<OnlineMovieList>, response: Response<OnlineMovieList>) {
                if (response.isSuccessful){
                    response.body()?.let { movieList ->
                        val popularMovies = mutableListOf<LocalMovieModel>()
                        movieList.results.forEach {
                            popularMovies.add(
                                LocalMovieModel(it.id.toLong(),it.original_title,"https://image.tmdb.org/t/p/w154${it.poster_path}")
                            )
                        }
                        val popularCover = CoverModel(3,"Popular",popularMovies)
                        if(!coverList.contains(popularCover)) {
                            coverList.add(popularCover)
                        }
                    }
                }
            }

            override fun onFailure(p0: Call<OnlineMovieList>, t: Throwable) {
                Log.e("RETROFIT","Network error: ${t.message}")
            }

        })
    }

    private fun getUpcomingMovies () {
        val call = Retrofit.movieApi.getUpcomingMovies(token)
        call.enqueue(object : Callback<OnlineMovieList> {
            override fun onResponse(call: Call<OnlineMovieList>, response: Response<OnlineMovieList>) {

                if (response.isSuccessful) {
                    response.body()?.let { movieList ->

                        val upcomingMovies = mutableListOf<LocalMovieModel>()

                        movieList.results.forEach {
                            upcomingMovies.add(
                                LocalMovieModel(it.id.toLong(),it.original_title,"https://image.tmdb.org/t/p/w154${it.poster_path}")
                            )
                        }
                        val upcomingCover = CoverModel(4,"Upcoming",upcomingMovies)
                        if(!coverList.contains(upcomingCover)) {
                            coverList.add(upcomingCover)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<OnlineMovieList>, t: Throwable) {
                Log.e("RETROFIT","Network error: ${t.message}")
            }
        })

    }

    private fun getMovieGenreList () {
        val call = Retrofit.movieApi.getMovieGenreList(token)
        call.enqueue(object : Callback<Genres> {
            override fun onResponse(p0: Call<Genres>, response: Response<Genres>) {
                if (response.isSuccessful){
                    response.body()?.let {
                        it.genres.forEach { genre ->
                            if (!categories.contains(genre)) {
                                categories.add(genre)
                            }
                        }
                    }
                }
            }

            override fun onFailure(p0: Call<Genres>, t: Throwable) {
                Log.e("RETROFIT","Network error: ${t.message}")
            }
        })
    }

    private fun getCovers(coverList: MutableList<CoverModel>) {
        coverAdapter = CoverAdapter()
        coverAdapter.setList(coverList)
        binding.rvCover.adapter = coverAdapter
    }
}