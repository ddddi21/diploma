package com.example.navigation.router

import android.os.Bundle
import com.example.feature_patient_details.presentation.PatientDetailsFragment
import com.example.feature_main_api.domain.model.Patient
import com.medicalSystem.splash.R
import com.example.navigation.navigation.NavControllerProvider
import javax.inject.Inject

class RouterImpl @Inject constructor(
    private val navControllerProvider: NavControllerProvider
) : Router {

    override fun toLogin() {
        navigateTo(R.id.action_splashFragment_to_authFragment)
    }

    override fun navigateToMain() {
        navigateTo(R.id.patientListsFragment)
    }

    override fun navigateToRegistration() {
        navigateTo(R.id.registrationFragment)
    }

    override fun navigateToAuthNavGraph() {
        navigateTo(R.id.auth_nav_graph)
    }

    override fun goToPreviousScreen() {
        navControllerProvider.get()?.popBackStack()
    }

    override fun navigateToPatientDetailsScreen(patient: Patient) {
        navigateTo(R.id.patientDetailsFragment, PatientDetailsFragment.buildArgs(patient))
    }

    override fun navigateToCheckDiagnosDetailsScreen(diagnos: String) {
        navigateTo(R.id.illnessIteractionCheckFragment)
    }

    override fun navigateToKidney() {
        navigateTo(R.id.kidneyCheckFragment)
    }

    override fun navigateToDiabet() {
        navigateTo(R.id.diabetCheckFragment)
    }

    override fun navigateToAuth() {
        navigateTo(R.id.authFragment)
    }

    override fun navigateToAddIteraction() {
        navigateTo(R.id.addIteractionFragment)
    }

    private fun navigateTo(actionId: Int, bundle: Bundle? = null) {
        navControllerProvider.get()
            ?.navigate(actionId, bundle)
    }
}