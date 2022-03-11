package in_.turker.moviesapp.base

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.NavOptions
import androidx.navigation.fragment.FragmentNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import in_.turker.moviesapp.utils.NavigateFragmentParams
import in_.turker.moviesapp.utils.SingleLiveEvent
import javax.inject.Inject

/**
 * Created by Kerem TÜRKER on 11.03.2022.
 */

@HiltViewModel
open class BaseViewModel
@Inject constructor(
    app: Application
) : AndroidViewModel(app){
    val navigateFragmentDetection by lazy { SingleLiveEvent<NavigateFragmentParams>() }

    fun navigateFragment(
        navAction: Int,
        bundle: Bundle? = null,
        navOptions: NavOptions? = null,
        extras: FragmentNavigator.Extras? = null
    ) {
        val params = NavigateFragmentParams(navAction, bundle, navOptions, extras)
        navigateFragmentDetection.postValue(params)
    }
}