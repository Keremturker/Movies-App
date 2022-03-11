package in_.turker.moviesapp.ui.fragment.main

import android.app.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import in_.turker.moviesapp.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by Kerem TÜRKER on 11.03.2022.
 */

@HiltViewModel
class MainVM @Inject constructor(
    myApp: Application
) : BaseViewModel(app = myApp)