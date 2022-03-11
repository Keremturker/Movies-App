package in_.turker.moviesapp.ui.fragment.main

import android.view.LayoutInflater
import android.view.ViewGroup
import in_.turker.moviesapp.BuildConfig
import in_.turker.moviesapp.base.BaseAdapter
import in_.turker.moviesapp.base.BaseHolder
import in_.turker.moviesapp.data.model.Result
import in_.turker.moviesapp.databinding.ItemNowPlayingBinding
import in_.turker.moviesapp.utils.loadImagesWithGlide

/**
 * Created by Kerem TÜRKER on 11.03.2022.
 */
class SliderAdapter :
    BaseAdapter<Result, ItemNowPlayingBinding, SliderHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SliderHolder {
        return SliderHolder(
            ItemNowPlayingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

}

class SliderHolder(
    viewBinding: ItemNowPlayingBinding
) :
    BaseHolder<Result, ItemNowPlayingBinding>(viewBinding) {
    override fun bind(binding: ItemNowPlayingBinding, item: Result?) {
        item?.let { result ->
            binding.apply {
                imgMoviePhoto.loadImagesWithGlide(BuildConfig.PHOTO_URL + result.posterPath)
                txtMovieTitle.text = item.title
                txtMovieDescription.text = item.overview
            }
        } ?: return
    }
}