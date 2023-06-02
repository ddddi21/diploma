package com.example.feature_main

import com.example.feature_main_api.domain.model.Patient
import com.medicalSystem.common.router.NavigateBackRouter

interface PatientRouter: NavigateBackRouter {

    fun navigateToPatientDetailsScreen(patient: Patient)
    fun navigateToCheckDiagnosDetailsScreen(diagnos: String)

    fun navigateToKidney()

    fun navigateToDiabet()
    fun navigateToAuth()
    fun navigateToAuthNavGraph()

    fun navigateToAddIteraction()

}