package in_.turker.moviesapp.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

/**
 * Created by Kerem TÜRKER on 11.03.2022.
 */


@BindingAdapter("loadImage")
fun ImageView.loadImagesWithGlide(url: String?) {
    Glide.with(this)
        .load(url)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}


fun <T> LiveData<T>.observeThis(owner: LifecycleOwner, function: (T) -> Unit) {
    observe(owner) {
        it?.let {
            function(it)
        }
    }
}


fun View.visibleIf(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}
