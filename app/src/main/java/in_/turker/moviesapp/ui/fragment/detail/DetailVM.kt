package in_.turker.moviesapp.ui.fragment.detail

import android.app.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import in_.turker.moviesapp.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by Kerem TÜRKER on 11.03.2022.
 */

@HiltViewModel
class DetailVM  @Inject constructor(
    myApp: Application
) : BaseViewModel(app = myApp)