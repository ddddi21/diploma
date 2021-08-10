package com.example.feature_collection.presentation.filmCollection.di

import androidx.fragment.app.Fragment
import com.example.feature_collection.presentation.filmCollection.WatchedCollectionFilmsFragment
import com.technokratos.common.di.scope.ScreenScope
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        WatchedCollectionFilmsModule::class
    ]
)
@ScreenScope
interface WatchedCollectionFilmsComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance fragment: Fragment
        ): WatchedCollectionFilmsComponent
    }

    fun inject(fragment: WatchedCollectionFilmsFragment)
}