package com.example.feature_collection.presentation.nestedFragments.di

import androidx.fragment.app.Fragment
import com.example.feature_collection.presentation.nestedFragments.WillWatchLaterFilmsFragment
import com.technokratos.common.di.scope.ScreenScope
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

    fun inject(fragment: WillWatchLaterFilmsFragment)
}