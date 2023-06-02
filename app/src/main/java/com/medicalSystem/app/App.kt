package com.medicalSystem.app

import android.app.Application
import com.medicalSystem.app.di.app.AppComponent
import com.medicalSystem.app.di.app.DaggerAppComponent
import com.medicalSystem.app.di.deps.FeatureHolderManager
import com.medicalSystem.common.base.ComponentDependenciesProvider
import com.medicalSystem.common.base.HasComponentDependencies
import com.medicalSystem.common.di.CommonApi
import com.medicalSystem.common.di.FeatureContainer
import javax.inject.Inject

open class App : Application(), FeatureContainer, HasComponentDependencies {

    @Inject lateinit var featureHolderManager: FeatureHolderManager

    @Inject
    override lateinit var dependencies: ComponentDependenciesProvider
        protected set

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()
        appComponent.inject(this)
    }

    override fun <T> getFeature(key: Class<*>): T {
        return featureHolderManager.getFeature<T>(key)!!
    }

    override fun releaseFeature(key: Class<*>) {
        featureHolderManager.releaseFeature(key)
    }

    override fun commonApi(): CommonApi {
        return appComponent
    }
}