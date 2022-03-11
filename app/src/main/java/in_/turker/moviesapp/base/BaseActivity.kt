package in_.turker.moviesapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import in_.turker.moviesapp.utils.NavigateFragmentParams

/**
 * Created by Kerem TÜRKER on 11.03.2022.
 */

abstract class BaseActivity<BindingType : ViewBinding, ViewModelType : BaseViewModel> :
    AppCompatActivity() {

    lateinit var binding: BindingType
    abstract fun onActivityCreated()
    abstract fun navigateFragment(params: NavigateFragmentParams)
    abstract fun observe()
    abstract fun getViewBinding(): BindingType
    protected abstract val viewModel: ViewModelType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        onActivityCreated()
        observe()
    }

}