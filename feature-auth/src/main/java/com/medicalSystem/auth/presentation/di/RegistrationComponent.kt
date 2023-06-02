package com.medicalSystem.auth.presentation.di

import androidx.fragment.app.Fragment
import com.medicalSystem.auth.presentation.registration.RegistrationFragment
import com.medicalSystem.common.di.scope.ScreenScope
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        RegistrationModule::class
    ]
)
@ScreenScope
interface RegistrationComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance fragment: Fragment
        ): RegistrationComponent
    }

    fun inject(fragment: RegistrationFragment)
}