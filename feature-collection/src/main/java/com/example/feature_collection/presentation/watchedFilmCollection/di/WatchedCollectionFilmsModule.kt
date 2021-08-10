package com.example.feature_collection.presentation.watchedFilmCollection.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.feature_collection.CollectionRouter
import com.example.feature_collection.presentation.watchedFilmCollection.WatchedCollectionFilmsViewModel
import com.technokratos.common.di.viewmodel.ViewModelKey
import com.technokratos.common.di.viewmodel.ViewModelModule
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
class WatchedCollectionFilmsModule {

    @Provides
    internal fun provideWatchedCollectionFilmsViewModelFromStore(fragment: Fragment, factory: ViewModelProvider.Factory): WatchedCollectionFilmsViewModel {
        return ViewModelProviders.of(fragment, factory).get(WatchedCollectionFilmsViewModel::class.java)
    }

    @Provides
    @IntoMap
    @ViewModelKey(WatchedCollectionFilmsViewModel::class)
    fun provideWatchedCollectionFilmsViewModel(router: CollectionRouter): ViewModel {
        return WatchedCollectionFilmsViewModel(router)
    }
}