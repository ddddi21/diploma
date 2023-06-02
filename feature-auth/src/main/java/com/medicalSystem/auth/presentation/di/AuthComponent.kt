package com.medicalSystem.auth.presentation.di

import androidx.fragment.app.Fragment
import com.medicalSystem.auth.presentation.auth.AuthFragment
import com.medicalSystem.common.di.scope.ScreenScope
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        AuthModule::class
    ]
)
@ScreenScope
interface AuthComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance fragment: Fragment
        ): AuthComponent
    }

    fun inject(fragment: AuthFragment)
}