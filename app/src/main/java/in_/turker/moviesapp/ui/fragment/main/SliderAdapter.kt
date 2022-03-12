package in_.turker.moviesapp.ui.fragment.main

import android.view.LayoutInflater
import android.view.ViewGroup
import in_.turker.moviesapp.base.BaseAdapter
import in_.turker.moviesapp.base.BaseHolder
import in_.turker.moviesapp.data.model.main.Result
import in_.turker.moviesapp.databinding.ItemNowPlayingBinding

/**
 * Created by Kerem TÜRKER on 11.03.2022.
 */
class SliderAdapter(
    private val onClickAction: ((Int) -> Unit)
) :
    BaseAdapter<Result, ItemNowPlayingBinding, SliderHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SliderHolder {
        return SliderHolder(
            ItemNowPlayingBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClickAction
        )
    }

}

class SliderHolder(
    viewBinding: ItemNowPlayingBinding,
    private val onClickAction: ((Int) -> Unit)
) :
    BaseHolder<Result, ItemNowPlayingBinding>(viewBinding) {
    override fun bind(binding: ItemNowPlayingBinding, item: Result?) {
        item?.let { result ->
            binding.apply {
                binding.item = result
                clParent.setOnClickListener {
                    onClickAction.invoke(result.id)
                }
            }
        } ?: return
    }
}