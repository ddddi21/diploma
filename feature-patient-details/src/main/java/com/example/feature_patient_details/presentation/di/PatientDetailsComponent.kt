package com.example.feature_patient_details.presentation.di

import androidx.fragment.app.Fragment
import com.example.feature_patient_details.presentation.PatientDetailsFragment
import com.medicalSystem.common.di.scope.ScreenScope
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        PatientDetailsModule::class
    ]
)
@ScreenScope
interface PatientDetailsComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance fragment: Fragment
        ): PatientDetailsComponent
    }

    fun inject(fragment: PatientDetailsFragment)
}