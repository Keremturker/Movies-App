package in_.turker.moviesapp.ui.fragment.main

import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint
import in_.turker.moviesapp.R
import in_.turker.moviesapp.base.BaseFragment
import in_.turker.moviesapp.data.model.Result
import in_.turker.moviesapp.databinding.FragmentMainBinding
import in_.turker.moviesapp.utils.ApiState
import in_.turker.moviesapp.utils.collect
import in_.turker.moviesapp.utils.collectLast
import in_.turker.moviesapp.utils.visibleIf
import kotlinx.coroutines.flow.collect
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
    private val sliderAdapter = SliderAdapter()
    private val sliderItemCount = 5

    override fun onFragmentCreated() {
        prepareRecyclerView()
        setListener()
        collectLast(viewModel.getUpcoming(), ::setMovie)


    }

    private fun prepareRecyclerView() {
        collect(flow = upcomingAdapter.loadStateFlow
            .distinctUntilChangedBy { it.source.refresh }
            .map { it.refresh },
            action = ::setUpComingUiState
        )
        binding.rvMovies.adapter = upcomingAdapter
    }


    private fun setListener() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            binding.progressBar.visibleIf(true)

            collectLast(viewModel.getUpcoming(), ::setMovie)
            viewModel.getNowPlaying()
        }

        binding.appbarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            binding.swipeRefreshLayout.isEnabled = verticalOffset == 0
        })

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateIndicator(position)
            }
        })
    }

    private fun addIndicators() {
        binding.llIndicator.removeAllViews()
        for (i in 1..sliderItemCount) {
            val view = ImageView(context)
            view.setImageResource(R.drawable.passive_dot)
            binding.llIndicator.addView(view)
        }
        updateIndicator(0)
    }

    private fun updateIndicator(position: Int) {
        var imageView: ImageView
        val lp =
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        lp.setMargins(8, 0, 8, 0)
        for (i in 0 until binding.llIndicator.childCount) {
            imageView = binding.llIndicator.getChildAt(i) as ImageView
            imageView.layoutParams = lp
            if (position == i) {
                imageView.setImageResource(R.drawable.active_dot)
            } else {
                imageView.setImageResource(R.drawable.passive_dot)
            }
        }
    }

    override fun observe() {
        lifecycleScope.launchWhenResumed {
            viewModel.onNowPlaying.collect {
                when (it) {
                    ApiState.Empty -> {}

                    ApiState.Loading -> {}

                    is ApiState.Failure -> {}

                    is ApiState.Success -> {
                        addIndicators()
                        binding.viewPager.adapter = sliderAdapter
                        sliderAdapter.replaceData(it.data?.take(sliderItemCount))
                    }
                }
            }
        }
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