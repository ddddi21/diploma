package com.example.feature_collection.model

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.LinearLayoutCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.feature_collection.R
import com.example.feature_collection.databinding.FilmItemViewGridBinding
import com.technokratos.common.base.adapter.Fillable
import com.technokratos.common.base.adapter.ViewType

class FilmGridItemView @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attributes, defStyleAttr), Fillable<FilmGridItem> {

    private val binding by lazy {
        FilmItemViewGridBinding.bind(this)
    }

    override fun fill(model: FilmGridItem) = with(binding) {
        val option = RequestOptions()
            .fallback(R.drawable.ic_film_poster_template)
            .placeholder(R.drawable.ic_film_poster_template)
        titleTextView.text = model.title
        Glide.with(context)
            .load(model.posterUrl)
            .apply(option)
            .into(posterImageView)
        rateTextView.text = model.rating.toString()
        setOnClickListener {
            model.onItemClicked?.invoke(model.id)
        }
    }
}

data class FilmGridItem(
    val id: Int,
    val title: String,
    val rating: Double,
    val posterUrl: String?,
    val description: String,
    val genres: List<String>,
    val onItemClicked: ((Int) -> Unit) ? = null
) : ViewType(R.layout.film_item_view_grid)