package in_.turker.moviesapp.ui.fragment.detail

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import in_.turker.moviesapp.R
import in_.turker.moviesapp.base.BaseFragment
import in_.turker.moviesapp.databinding.FragmentDetailBinding
import in_.turker.moviesapp.utils.*
import kotlinx.coroutines.flow.collect

/**
 * Created by Kerem TÜRKER on 11.03.2022.
 */

@AndroidEntryPoint
class FragmentDetail : BaseFragment<FragmentDetailBinding, DetailVM>() {
    override val viewModel: DetailVM by viewModels()

    override fun getViewBinding() = FragmentDetailBinding.inflate(layoutInflater)


    override fun onFragmentCreated() {
        val movieId = arguments?.getInt(MOVIE_ID) ?: 0
        viewModel.getMovieDetail(movieId)
    }

    override fun observe() {
        lifecycleScope.launchWhenResumed {
            viewModel.onMovieDetail.collect {
                when (it) {
                    ApiState.Empty -> {}

                    ApiState.Loading -> {
                        binding.txtError.visibleIf(false)
                        binding.clDetail.visibleIf(false)
                        binding.progressBar.visibleIf(true)
                    }

                    is ApiState.Failure -> {
                        binding.progressBar.visibleIf(false)
                        binding.clDetail.visibleIf(false)
                        binding.errorText =
                            it.errorMessage ?: getString(R.string.something_went_wrong)

                        binding.txtError.visibleIf(true)
                    }

                    is ApiState.Success -> {
                        binding.progressBar.visibleIf(false)
                        binding.txtError.visibleIf(false)
                        binding.clDetail.visibleIf(true)

                        binding.apply {
                            it.data?.let { detail ->
                                detail.releaseDate = detail.releaseDate.convertToDateFormat(
                                    DATE_FORMAT_SERVER, DATE_FORMAT_CLIENT
                                )
                                binding.item = detail
                            }
                        }
                    }
                }
            }
        }
    }
}