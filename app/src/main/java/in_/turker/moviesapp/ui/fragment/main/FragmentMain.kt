package in_.turker.moviesapp.ui.fragment.main

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import in_.turker.moviesapp.base.BaseFragment
import in_.turker.moviesapp.databinding.FragmentMainBinding

/**
 * Created by Kerem TÜRKER on 11.03.2022.
 */

@AndroidEntryPoint
class FragmentMain : BaseFragment<FragmentMainBinding, MainVM>() {
    override val viewModel: MainVM by viewModels()

    override fun getViewBinding() = FragmentMainBinding.inflate(layoutInflater)


    override fun onFragmentCreated() {}
}