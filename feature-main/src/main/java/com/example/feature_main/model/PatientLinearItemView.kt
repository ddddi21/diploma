package com.example.feature_main.model

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.feature_main.R
import com.example.feature_main.databinding.PatientItemViewLinearBinding
import com.medicalSystem.common.base.adapter.Fillable
import com.medicalSystem.common.base.adapter.ViewType

class PatientLinearItemView @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attributes, defStyleAttr), Fillable<PatientLinearItem> {

    private val binding by lazy {
        PatientItemViewLinearBinding.bind(this)
    }

    override fun fill(model: PatientLinearItem) = with(binding) {
        patientTitleTextView.text = model.name
        setOnClickListener {
            model.onItemClicked?.invoke()
        }
    }
}

data class PatientLinearItem(
    val id: Int,
    val name: String,
    val onItemClicked: (() -> Unit)? = null
) : ViewType(R.layout.patient_item_view_linear)