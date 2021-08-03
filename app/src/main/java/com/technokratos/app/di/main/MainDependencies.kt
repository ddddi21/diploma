package com.technokratos.app.di.main

import com.example.navigation.navigation.NavControllerProvider
import com.technokratos.app.di.deps.ComponentDependencies

interface MainDependencies : ComponentDependencies {

    fun navControllerProviderDeps(): NavControllerProvider
}