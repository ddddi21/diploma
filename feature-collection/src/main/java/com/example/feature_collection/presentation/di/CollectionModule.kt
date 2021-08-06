package com.example.feature_collection.presentation.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.feature_collection.CollectionRouter
import com.example.feature_collection.presentation.CollectionViewModel
import com.technokratos.common.di.viewmodel.ViewModelKey
import com.technokratos.common.di.viewmodel.ViewModelModule
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
class CollectionModule {

    @Provides
    internal fun provideCollectionViewModelFromStore(fragment: Fragment, factory: ViewModelProvider.Factory): CollectionViewModel {
        return ViewModelProviders.of(fragment, factory).get(CollectionViewModel::class.java)
    }

    @Provides
    @IntoMap
    @ViewModelKey(CollectionViewModel::class)
    fun provideCollectionViewModelFromStore(router: CollectionRouter): ViewModel {
        return CollectionViewModel(router)
    }
}