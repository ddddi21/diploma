package com.example.feature_collection.model

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.feature_collection.databinding.FilmItemViewMiniBinding
import com.technokratos.common.base.adapter.Fillable

class FilmVerticalItemView @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attributes, defStyleAttr), Fillable<FilmMini> {

    private val binding by lazy {
        FilmItemViewMiniBinding.bind(this)
    }

    override fun fill(model: FilmMini) = with(binding) {
        filmTitleTextView.text = model.title
    }
}