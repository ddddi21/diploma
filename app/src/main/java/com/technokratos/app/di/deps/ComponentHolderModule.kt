package com.technokratos.app.di.deps

import com.example.feature_collection.di.CollectionFeatureHolder
import com.example.feature_collection.di.CollectionFeatureKey
import com.example.feature_film_details.di.FilmDetailsFeatureHolder
import com.example.feature_film_details.di.FilmDetailsFeatureKey
import com.technokratos.app.App
import com.technokratos.auth.di.auth.AuthFeatureHolder
import com.technokratos.auth.di.auth.AuthFeatureKey
import com.technokratos.auth.di.registration.RegistrationFeatureHolder
import com.technokratos.auth.di.registration.RegistrationFeatureKey
import com.technokratos.common.di.FeatureApiHolder
import com.technokratos.common.di.FeatureContainer
import com.technokratos.common.di.scope.ApplicationScope
import com.technokratos.core_db.di.DbApi
import com.technokratos.core_db.di.DbHolder
import com.technokratos.splash.di.SplashFeatureKey
import com.technokratos.splash.di.SplashFeatureHolder
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
interface ComponentHolderModule {

    @ApplicationScope
    @Binds
    fun provideFeatureContainer(application: App): FeatureContainer

    @ApplicationScope
    @Binds
    @ClassKey(SplashFeatureKey::class)
    @IntoMap
    fun provideSplashFeatureHolder(splashFeatureHolder: SplashFeatureHolder): FeatureApiHolder

    @ApplicationScope
    @Binds
    @ClassKey(DbApi::class)
    @IntoMap
    fun provideDbFeature(dbHolder: DbHolder): FeatureApiHolder

    @ApplicationScope
    @Binds
    @ClassKey(AuthFeatureKey::class)
    @IntoMap
    fun provideAuthFeatureHolder(authFeatureHolder: AuthFeatureHolder): FeatureApiHolder

    @ApplicationScope
    @Binds
    @ClassKey(RegistrationFeatureKey::class)
    @IntoMap
    fun provideRegistrationFeatureHolder(registrationFeatureHolder: RegistrationFeatureHolder): FeatureApiHolder

    @ApplicationScope
    @Binds
    @ClassKey(CollectionFeatureKey::class)
    @IntoMap
    fun provideCollectionFeatureHolder(collectionFeatureHolder: CollectionFeatureHolder): FeatureApiHolder

    @ApplicationScope
    @Binds
    @ClassKey(FilmDetailsFeatureKey::class)
    @IntoMap
    fun provideFilmDetailsFeatureHolder(filmDetailsFeatureHolder: FilmDetailsFeatureHolder): FeatureApiHolder
}