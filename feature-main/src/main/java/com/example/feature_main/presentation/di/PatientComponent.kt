package com.example.feature_main.presentation.di

import androidx.fragment.app.Fragment
import com.example.feature_main.presentation.profile.ProfileFragment
import com.example.feature_main.presentation.diagnos.DiabetCheckFragment
import com.example.feature_main.presentation.diagnos.KidneyCheckFragment
import com.example.feature_main.presentation.diagnos.MainDiagnosCheckListFragment
import com.example.feature_main.presentation.illnessIteraction.IllnessIteractionFragment
import com.example.feature_main.presentation.profile.AddInteractionFragment
import com.medicalSystem.common.di.scope.ScreenScope
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        PatientModule::class
    ]
)
@ScreenScope
interface PatientComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance fragment: Fragment
        ): PatientComponent
    }

    fun inject(fragment: KidneyCheckFragment)

    fun inject(fragment: DiabetCheckFragment)

    fun inject(fragment: MainDiagnosCheckListFragment)

    fun inject(fragment: IllnessIteractionFragment)

    fun inject(fragment: ProfileFragment)
    fun inject(fragment: AddInteractionFragment)

}