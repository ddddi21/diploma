package com.example.navigation.router

import com.technokratos.auth.router.AuthRouter
import com.technokratos.auth.router.RegistrationRouter
import com.technokratos.common.di.scope.ApplicationScope
import com.technokratos.splash.SplashRouter
import com.technokratos.users.UsersRouter
import dagger.Binds
import dagger.Module

@Module
abstract class RouterModule {

    @ApplicationScope
    @Binds
    abstract fun provideUsersRouter(routerImpl: RouterImpl): UsersRouter

    @ApplicationScope
    @Binds
    abstract fun provideSplashRouter(routerImpl: RouterImpl): SplashRouter

    @ApplicationScope
    @Binds
    abstract fun provideAuthRouter(routerImpl: RouterImpl): AuthRouter

    @ApplicationScope
    @Binds
    abstract fun provideRegistrationRouter(routerImpl: RouterImpl): RegistrationRouter
}