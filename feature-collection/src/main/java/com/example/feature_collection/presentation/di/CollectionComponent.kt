package com.example.feature_collection.presentation.di

import androidx.fragment.app.Fragment
import com.example.feature_collection.presentation.CollectionFragment
import com.technokratos.common.di.scope.ScreenScope
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        CollectionModule::class
    ]
)
@ScreenScope
interface CollectionComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance fragment: Fragment
        ): CollectionComponent
    }

    fun inject(fragment: CollectionFragment)
}