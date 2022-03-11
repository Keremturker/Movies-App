package in_.turker.moviesapp.ui

import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import in_.turker.moviesapp.base.BaseActivity
import in_.turker.moviesapp.databinding.ActivitySingleBinding

@AndroidEntryPoint
class SingleActivity : BaseActivity<ActivitySingleBinding, SingleVM>(){

    override val viewModel: SingleVM by viewModels()

    override fun getViewBinding() = ActivitySingleBinding.inflate(layoutInflater)

    override fun onActivityCreated() {}

    override fun observe() {}

}