package com.medicalSystem.app.di.deps

import com.example.feature_main.di.PatientFeatureHolder
import com.example.feature_main.di.PatientFeatureKey
import com.example.feature_patient_details.di.PatientDetailsFeatureHolder
import com.example.feature_patient_details.di.PatientDetailsFeatureKey
import com.medicalSystem.app.App
import com.medicalSystem.auth.di.AuthFeatureHolder
import com.medicalSystem.auth.di.AuthFeatureKey
import com.medicalSystem.common.di.FeatureApiHolder
import com.medicalSystem.common.di.FeatureContainer
import com.medicalSystem.common.di.scope.ApplicationScope
import com.medicalSystem.core_db.di.DbApi
import com.medicalSystem.core_db.di.DbHolder
import com.medicalSystem.splash.di.SplashFeatureKey
import com.medicalSystem.splash.di.SplashFeatureHolder
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
    @ClassKey(PatientFeatureKey::class)
    @IntoMap
    fun provideCollectionFeatureHolder(patientFeatureHolder: PatientFeatureHolder): FeatureApiHolder

    @ApplicationScope
    @Binds
    @ClassKey(PatientDetailsFeatureKey::class)
    @IntoMap
    fun provideFilmDetailsFeatureHolder(patientDetailsFeatureHolder: PatientDetailsFeatureHolder): FeatureApiHolder
}