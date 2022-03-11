package in_.turker.moviesapp.utils

import android.os.Bundle
import androidx.navigation.NavOptions
import androidx.navigation.fragment.FragmentNavigator

/**
 * Created by Kerem TÜRKER on 11.03.2022.
 */

data class NavigateFragmentParams(
    val navAction: Int,
    val bundle: Bundle? = null,
    val navOptions: NavOptions? = null,
    val extras: FragmentNavigator.Extras? = null
)
