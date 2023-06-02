package com.medicalSystem.core_db.di

import com.medicalSystem.common.di.CommonApi
import com.medicalSystem.common.di.scope.ApplicationScope
import dagger.Component

@Component(
    modules = [
        DbModule::class
    ],
    dependencies = [
        DbDependencies::class
    ]
)
@ApplicationScope
abstract class DbComponent : DbApi {

    @Component(
        dependencies = [
            CommonApi::class
        ]
    )
    interface DbDependenciesComponent : DbDependencies
}