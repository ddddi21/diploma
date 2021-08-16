package com.example.feature_film_details.presentation.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.feature_film_details.FilmDetailsRouter
import com.example.feature_film_details.presentation.FilmDetailsViewModel
import com.technokratos.common.di.viewmodel.ViewModelKey
import com.technokratos.common.di.viewmodel.ViewModelModule
import com.technokratos.common.router.NavigateBackRouter
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
class FilmDetailsModule {

    @Provides
    internal fun provideFilmDetailsViewModelFromStore(fragment: Fragment, factory: ViewModelProvider.Factory): FilmDetailsViewModel {
        return ViewModelProviders.of(fragment, factory).get(FilmDetailsViewModel::class.java)
    }

    @Provides
    @IntoMap
    @ViewModelKey(FilmDetailsViewModel::class)
    fun provideFilmDetailsViewModel(router: FilmDetailsRouter, navigateBackRouter: NavigateBackRouter): ViewModel {
        return FilmDetailsViewModel(router, navigateBackRouter)
    }
}