package com.example.feature_patient_details.presentation

import androidx.lifecycle.viewModelScope
import com.example.feature_main_api.domain.model.Film
import com.example.feature_patient_details.PatientDetailsRouter
import com.example.feature_patient_details.domain.PatientDetailsInteractor
import com.medicalSystem.common.base.BaseViewModel
import kotlinx.coroutines.launch

class PatientDetailsViewModel(
    private var router: PatientDetailsRouter,
    private val interactor: PatientDetailsInteractor
) : BaseViewModel() {

    private lateinit var film: Film

    fun onBackClicked() {
        router.goToPreviousScreen()
    }

}