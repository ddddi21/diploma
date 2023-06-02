package com.medicalSystem.app.di.app

import android.content.Context
import com.medicalSystem.app.App
import com.medicalSystem.common.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @ApplicationScope
    @Provides
    fun provideContext(application: App): Context {
        return application
    }
}