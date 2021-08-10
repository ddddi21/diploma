package com.example.feature_collection.model

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.feature_collection.databinding.FilmItemViewBinding
import com.technokratos.common.base.adapter.Fillable

class FilmItemView @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attributes, defStyleAttr), Fillable<Film> {

    private val binding by lazy {
        FilmItemViewBinding.bind(this)
    }

    override fun fill(model: Film) {
//        TODO("Not yet implemented")
    }
}