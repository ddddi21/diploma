package com.technokratos.common.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.technokratos.common.R

fun Activity.showShortToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Activity.showShortToast(@StringRes msg: Int) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

@SuppressLint("NewApi")
fun Activity.setBarColorBackground(colorId: Int) {
    window.statusBarColor = ContextCompat.getColor(this, colorId)
}

fun <T> MutableLiveData<T>.setValueIfNew(newValue: T) {
    if (this.value != newValue) value = newValue
}

fun <T> MutableLiveData<T>.postValueIfNew(newValue: T) {
    if (this.value != newValue) postValue(newValue)
}

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun View.makeInvisible() {
    this.visibility = View.INVISIBLE
}

fun View.makeGone() {
    this.visibility = View.GONE
}

fun RecyclerView.setDivider(@DrawableRes drawableRes: Int) {
    val divider = DividerItemDecoration(
        this.context,
        DividerItemDecoration.VERTICAL
    )
    val drawable = ContextCompat.getDrawable(
        this.context,
        drawableRes
    )
    drawable?.let {
        divider.setDrawable(it)
        addItemDecoration(divider)
    }
}

fun <T : RecyclerView> T.removeItemDecorations() {
    while (itemDecorationCount > 0) {
        removeItemDecorationAt(0)
    }
}

fun View.setChip(text: String, chipGroup: ChipGroup) {
    val chip = Chip(context).apply {
        isCheckable = false
        chipStrokeColor = ContextCompat.getColorStateList(context, R.color.black)
        chipStrokeWidth = resources.getDimension(R.dimen.chip_stroke_width)
        setText(text)
        setTextAppearanceResource(R.style.ChipTextStyle)
        chipBackgroundColor = ContextCompat.getColorStateList(context, R.color.white)
    }
    chipGroup.addView(chip)
    val parameter = chip.layoutParams as ChipGroup.LayoutParams
    val marginValue = resources.getDimension(R.dimen.dimen_4dp).toInt()
    parameter.setMargins(marginValue, marginValue, marginValue, marginValue)
    chip.layoutParams = parameter
}
