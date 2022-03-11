package in_.turker.moviesapp.ui.fragment.main

import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint
import in_.turker.moviesapp.base.BaseFragment
import in_.turker.moviesapp.data.model.Result
import in_.turker.moviesapp.databinding.FragmentMainBinding
import in_.turker.moviesapp.utils.collect
import in_.turker.moviesapp.utils.collectLast
import in_.turker.moviesapp.utils.visibleIf
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Kerem TÜRKER on 11.03.2022.
 */

@AndroidEntryPoint
class FragmentMain : BaseFragment<FragmentMainBinding, MainVM>() {
    override val viewModel: MainVM by viewModels()

    override fun getViewBinding() = FragmentMainBinding.inflate(layoutInflater)

    @Inject
    lateinit var upcomingAdapter: UpcomingAdapter

    override fun onFragmentCreated() {

        setAdapter()
        collectLast(viewModel.getUpcoming(), ::setMovie)

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            binding.progressBar.visibleIf(true)
            collectLast(viewModel.getUpcoming(), ::setMovie)
        }

        binding.appbarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            binding.swipeRefreshLayout.isEnabled = verticalOffset == 0
        })

    }

    private fun setAdapter() {
        collect(flow = upcomingAdapter.loadStateFlow
            .distinctUntilChangedBy { it.source.refresh }
            .map { it.refresh },
            action = ::setUpComingUiState
        )
        binding.rvMovies.adapter = upcomingAdapter

    }

    private fun setUpComingUiState(loadState: LoadState) {
        when (loadState) {
            is LoadState.Loading -> {}

            is LoadState.NotLoading -> {
                binding.rvMovies.visibleIf(true)
                binding.progressBar.visibleIf(false)
            }

            is LoadState.Error -> {
                binding.rvMovies.visibleIf(false)
                binding.progressBar.visibleIf(false)
            }
        }
    }

    private suspend fun setMovie(itemsPagingData: PagingData<Result>) {
        upcomingAdapter.submitData(itemsPagingData)
    }
}