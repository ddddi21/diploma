package com.example.feature_collection.model

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.LinearLayoutCompat
import com.bumptech.glide.Glide
import com.example.feature_collection.R
import com.example.feature_collection.databinding.FilmItemViewBinding
import com.technokratos.common.base.adapter.Fillable
import com.technokratos.common.base.adapter.ViewType

class FilmItemView @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attributes, defStyleAttr), Fillable<Film> {

    private val binding by lazy {
        FilmItemViewBinding.bind(this)
    }

    override fun fill(model: Film) = with(binding) {
        titleTextView.text = model.title
        Glide.with(context)
            .load(model.posterUrl)
            .into(posterImageView)
        rateTextView.text = model.rating.toString()
    }
}

data class Film(
    val id: Int,
    val title: String,
    val rating: Double,
    val posterUrl: String,
    val onItemClicked: ((Int) -> Unit) ? = null
) : ViewType(R.layout.film_item_view)