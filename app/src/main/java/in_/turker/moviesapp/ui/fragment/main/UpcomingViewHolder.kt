package in_.turker.moviesapp.ui.fragment.main

import androidx.recyclerview.widget.RecyclerView
import in_.turker.moviesapp.BuildConfig
import in_.turker.moviesapp.data.model.Result
import in_.turker.moviesapp.databinding.ItemUpcomingBinding
import in_.turker.moviesapp.utils.loadImagesWithGlide

/**
 * Created by Kerem TÜRKER on 11.03.2022.
 */

class UpcomingViewHolder(private val binding: ItemUpcomingBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Result) {
        binding.apply {
            txtDate.text = item.releaseDate
            txtMovieDescription.text = item.overview
            txtMovieTitle.text = item.title

            imgMoviePhoto.loadImagesWithGlide("${BuildConfig.PHOTO_URL}${item.posterPath}")
        }
    }
}