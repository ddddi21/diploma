package com.example.feature_film_details.presentation.di

import androidx.fragment.app.Fragment
import com.example.feature_film_details.presentation.FilmDetailsFragment
import com.technokratos.common.di.scope.ScreenScope
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        FilmDetailsModule::class
    ]
)
@ScreenScope
interface FilmDetailsComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance fragment: Fragment
        ): FilmDetailsComponent
    }

    fun inject(fragment: FilmDetailsFragment)
}