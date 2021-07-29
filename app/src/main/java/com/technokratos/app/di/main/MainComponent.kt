package com.technokratos.app.di.main

import androidx.appcompat.app.AppCompatActivity
import com.technokratos.app.MainActivity
import com.technokratos.app.di.app.NavigationModule
import com.technokratos.app.navigation.NavControllerProvider
import com.technokratos.common.di.scope.ApplicationScope
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [
        MainDependencies::class
    ],
    modules = [
        MainModule::class,
        NavigationModule::class
    ]
)
@ApplicationScope
interface MainComponent {

    companion object {

        fun init(activity: AppCompatActivity, deps: MainDependencies): MainComponent {
            return DaggerMainComponent.factory().create(activity, deps)
        }
    }

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance activity: AppCompatActivity,
            deps: MainDependencies
        ): MainComponent
    }

    fun inject(mainActivity: MainActivity)

    fun provideNavControllerProvider(): NavControllerProvider
}