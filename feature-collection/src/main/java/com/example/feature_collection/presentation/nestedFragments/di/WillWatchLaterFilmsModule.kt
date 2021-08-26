package com.example.feature_collection.presentation.nestedFragments.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.feature_collection.CollectionRouter
import com.example.feature_collection.domain.CollectionInteractor
import com.example.feature_collection.presentation.mapper.FilmIntoPresentationFilmMapper
import com.example.feature_collection.presentation.nestedFragments.WillWatchLaterFilmsViewModel
import com.technokratos.common.di.viewmodel.ViewModelKey
import com.technokratos.common.di.viewmodel.ViewModelModule
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
class WillWatchLaterFilmsModule {

    @Provides
    internal fun provideWillWatchLaterFilmsViewModelFromStore(fragment: Fragment, factory: ViewModelProvider.Factory): WillWatchLaterFilmsViewModel {
        return ViewModelProviders.of(fragment, factory).get(WillWatchLaterFilmsViewModel::class.java)
    }

    @Provides
    @IntoMap
    @ViewModelKey(WillWatchLaterFilmsViewModel::class)
    fun provideWillWatchLaterFilmsViewModel(
        router: CollectionRouter,
        interactor: CollectionInteractor,
        filmIntoGridFilmMapper: FilmIntoPresentationFilmMapper
    ): ViewModel {
        return WillWatchLaterFilmsViewModel(router, interactor, filmIntoGridFilmMapper)
    }
}