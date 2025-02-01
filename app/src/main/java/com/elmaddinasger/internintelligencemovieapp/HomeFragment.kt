package com.elmaddinasger.internintelligencemovieapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import com.elmaddinasger.internintelligencemovieapp.models.MovieModel
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var movieSlideAdapter: MovieSlideAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var coverAdapter: CoverAdapter
    private lateinit var viewPager: ViewPager2
    private var currentPosition = 0

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
        getCategories(categories)
        getCovers(coverList)
        changeTab()
        startAutoMovieChange()
        getCovers(coverList)

    }



    private val categories = listOf(
        "All", "Action", "Adventure", "Animation", "Comedy", "Crime", "Documentary", "Drama",
        "Fantasy", "Historical", "Horror", "Musical", "Mystery", "Romance", "Science Fiction (Sci-Fi)",
        "Thriller", "War", "Western")

    private val images = mutableListOf(
        MovieModel(1,"https://fastly.picsum.photos/id/866/200/300.jpg?hmac=rcadCENKh4rD6MAp6V_ma-AyWv641M4iiOpe1RyFHeI"),
        MovieModel(2,"https://picsum.photos/seed/picsum/200/300"),
        MovieModel(3,"https://picsum.photos/200/300?grayscale"),
        MovieModel(4,"https://picsum.photos/id/237/200/300"),
        MovieModel(5,"https://picsum.photos/seed/picsum/200/300"),
        MovieModel(6,"https://picsum.photos/200/300?grayscale"))

    private val coverList = mutableListOf(
        CoverModel(1,"Most Popular", images),
        CoverModel(2,"Most Popular", images),
        CoverModel(3,"Most Popular", images),
        CoverModel(4,"Most Popular", images),
        CoverModel(5,"Most Popular", images),
        CoverModel(6,"Most Popular", images),
        CoverModel(7,"Most Popular", images),
        CoverModel(8,"Most Popular", images)
    )

    private fun getViewPager (movieList: List<MovieModel>){
        movieSlideAdapter = MovieSlideAdapter(movieList)
        viewPager = binding.vpHeaderMovieSlide
        binding.vpHeaderMovieSlide.adapter = movieSlideAdapter
    }

    private fun getCategories (categories: List<String>) {
        categoryAdapter = CategoryAdapter(categories)
        binding.rvCategories.adapter = categoryAdapter
    }

    private fun getCovers(coverList: MutableList<CoverModel>) {
        coverAdapter = CoverAdapter()
        coverAdapter.setList(coverList)
        binding.rvCover.adapter = coverAdapter
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
}