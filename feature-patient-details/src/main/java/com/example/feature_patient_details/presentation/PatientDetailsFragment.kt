package com.example.feature_patient_details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.feature_patient_details.databinding.PatientDetailsFragmentBinding
import com.example.feature_patient_details.di.PatientDetailsFeatureComponent
import com.example.feature_patient_details.di.PatientDetailsFeatureKey
import com.example.feature_main_api.domain.model.Patient
import com.example.feature_patient_details.data.repository.PatientMain
import com.medicalSystem.common.base.BaseFragment
import com.medicalSystem.common.di.FeatureUtils

private const val KEY = "PATIENT_KEY"

class PatientDetailsFragment : BaseFragment<PatientDetailsViewModel>() {

    companion object {
        fun buildArgs(patient: Patient) = bundleOf(KEY to patient)
    }

    private val patient by lazy {
        arguments?.getSerializable(KEY) as Patient
    }

    private lateinit var binding: PatientDetailsFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = PatientDetailsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun inject() {
        FeatureUtils.getFeature<PatientDetailsFeatureComponent>(this, PatientDetailsFeatureKey::class.java)
            .filmDetailsComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun initViews() {
        setUI(PatientMain.getPatient())
        binding.patientDetailsBack.setOnClickListener {
            viewModel.onBackClicked()
        }
    }

    override fun subscribe(viewModel: PatientDetailsViewModel) {
//        TODO("Not yet implemented")
    }

    private fun setUI(patient: Patient) = with(binding) {
//        val option = RequestOptions()
//            .fallback(R.drawable.ic_film_poster_template)
//            .placeholder(R.drawable.ic_film_poster_template)
//        Glide.with(requireContext())
//            .load(film.posterUrl)
//            .apply(option)
//            .into(posterImageView)
        patientDetailsName.text = patient.name
        patientDetailsAge.text = patient.age
        patientDetailsSex.text = patient.sex
        patientDetailsIllnesses.text = patient.illness
        patientDetailsDrugs.text = patient.medicaments
    }
}