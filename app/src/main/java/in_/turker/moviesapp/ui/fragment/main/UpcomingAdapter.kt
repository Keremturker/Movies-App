package in_.turker.moviesapp.ui.fragment.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import in_.turker.moviesapp.data.model.main.Result
import in_.turker.moviesapp.databinding.ItemUpcomingBinding
import in_.turker.moviesapp.utils.DATE_FORMAT_CLIENT
import in_.turker.moviesapp.utils.DATE_FORMAT_SERVER
import in_.turker.moviesapp.utils.convertToDateFormat

/**
 * Created by Kerem TÜRKER on 11.03.2022.
 */

class UpcomingAdapter(private val onClickAction: ((Int) -> Unit)) :
    PagingDataAdapter<Result, UpcomingViewHolder>(Comparator) {

    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        getItem(position)?.let { result -> holder.bind(result) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
        val binding =
            ItemUpcomingBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return UpcomingViewHolder(binding, onClickAction)
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

class UpcomingViewHolder(
    private val binding: ItemUpcomingBinding,
    private val onClickAction: ((Int) -> Unit)
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Result) {
        binding.apply {
            item.releaseDate = item.releaseDate.convertToDateFormat(
                DATE_FORMAT_SERVER, DATE_FORMAT_CLIENT
            )

            binding.item = item

            clParent.setOnClickListener {
                onClickAction.invoke(item.id)
            }
        }
    }
}