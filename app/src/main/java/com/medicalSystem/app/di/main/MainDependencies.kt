package com.medicalSystem.app.di.main

import com.example.navigation.navigation.NavControllerProvider
import com.example.navigation.router.MainRouter
import com.medicalSystem.common.base.ComponentDependencies

interface MainDependencies : ComponentDependencies {

    fun navControllerProviderDeps(): NavControllerProvider

    fun mainRouter(): MainRouter
}