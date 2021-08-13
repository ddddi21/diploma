package com.example.feature_collection.model

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.feature_collection.R
import com.example.feature_collection.databinding.FilmItemViewLinearBinding
import com.technokratos.common.base.adapter.Fillable
import com.technokratos.common.base.adapter.ViewType

class FilmVerticalItemView @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attributes, defStyleAttr), Fillable<FilmLinear> {

    private val binding by lazy {
        FilmItemViewLinearBinding.bind(this)
    }

    override fun fill(model: FilmLinear) = with(binding) {
        filmTitleTextView.text = model.title
    }
}

data class FilmLinear(
    val id: Int,
    val title: String,
    val onItemClicked: ((Int) -> Unit)? = null
) : ViewType(R.layout.film_item_view_linear)