package in_.turker.moviesapp.ui.fragment.detail

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import in_.turker.moviesapp.base.BaseFragment
import in_.turker.moviesapp.databinding.FragmentDetailBinding
import in_.turker.moviesapp.ui.fragment.main.MainVM

/**
 * Created by Kerem TÜRKER on 11.03.2022.
 */

@AndroidEntryPoint
class FragmentDetail : BaseFragment<FragmentDetailBinding, MainVM>() {
    override val viewModel: MainVM by viewModels()

    override fun getViewBinding() = FragmentDetailBinding.inflate(layoutInflater)


    override fun onFragmentCreated() {}
}