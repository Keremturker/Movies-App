package in_.turker.moviesapp.ui.fragment.main

import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.paging.PagingData
import dagger.hilt.android.AndroidEntryPoint
import in_.turker.moviesapp.base.BaseFragment
import in_.turker.moviesapp.data.Result
import in_.turker.moviesapp.databinding.FragmentMainBinding
import in_.turker.moviesapp.utils.collect
import in_.turker.moviesapp.utils.collectLast
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
    lateinit var nowPlayingAdapter: NowPlayingAdapter

    override fun onFragmentCreated() {

        setAdapter()
        collectLast(viewModel.getNowPlaying(), ::setMovie)

    }

    private fun setAdapter() {
        collect(flow = nowPlayingAdapter.loadStateFlow
            .distinctUntilChangedBy { it.source.refresh }
            .map { it.refresh },
            action = ::setReposUiState
        )
    }

    private fun setReposUiState(loadState: LoadState) {
        when (loadState) {
            is LoadState.Loading -> {}

            is LoadState.NotLoading -> {}

            is LoadState.Error -> {}
        }
    }

    private suspend fun setMovie(itemsPagingData: PagingData<Result>) {
        nowPlayingAdapter.submitData(itemsPagingData)
    }
}