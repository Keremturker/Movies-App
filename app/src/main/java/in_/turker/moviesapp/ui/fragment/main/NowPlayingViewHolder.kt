package in_.turker.moviesapp.ui.fragment.main

import androidx.recyclerview.widget.RecyclerView
import in_.turker.moviesapp.data.Result
import in_.turker.moviesapp.databinding.ItemNowPlayingBinding

/**
 * Created by Kerem TÜRKER on 11.03.2022.
 */

class NowPlayingViewHolder (private val binding: ItemNowPlayingBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Result) {
        binding.apply {}
    }
}