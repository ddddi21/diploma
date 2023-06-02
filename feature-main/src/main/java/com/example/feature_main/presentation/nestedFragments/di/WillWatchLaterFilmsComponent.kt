package com.example.feature_main.presentation.nestedFragments.di

import androidx.fragment.app.Fragment
import com.example.feature_main.presentation.nestedFragments.PatientsListsFragment
import com.medicalSystem.common.di.scope.ScreenScope
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        WillWatchLaterFilmsModule::class
    ]
)
@ScreenScope
interface WillWatchLaterFilmsComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance fragment: Fragment
        ): WillWatchLaterFilmsComponent
    }

    fun inject(fragment: PatientsListsFragment)
}