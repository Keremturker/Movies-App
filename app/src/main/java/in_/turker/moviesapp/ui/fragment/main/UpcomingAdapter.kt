package in_.turker.moviesapp.ui.fragment.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import in_.turker.moviesapp.BuildConfig
import in_.turker.moviesapp.data.model.Result
import in_.turker.moviesapp.databinding.ItemUpcomingBinding
import in_.turker.moviesapp.utils.loadImagesWithGlide
import javax.inject.Inject

/**
 * Created by Kerem TÜRKER on 11.03.2022.
 */

class UpcomingAdapter  @Inject constructor() :
    PagingDataAdapter<Result, UpcomingViewHolder>(Comparator) {

    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        getItem(position)?.let { result -> holder.bind(result) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
        val binding = ItemUpcomingBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return UpcomingViewHolder(binding)
    }

    object Comparator : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Result,
            newItem: Result
        ): Boolean {
            return oldItem == newItem
        }
    }
}

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