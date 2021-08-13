package com.example.navigation.router

import com.example.feature_collection.CollectionRouter
import com.example.feature_film_details.FilmDetailsRouter
import com.technokratos.auth.router.AuthRouter
import com.technokratos.common.di.scope.ApplicationScope
import com.technokratos.common.router.NavigateBackRouter
import com.technokratos.splash.SplashRouter
import dagger.Binds
import dagger.Module

@Module
abstract class RouterModule {

    @ApplicationScope
    @Binds
    abstract fun provideSplashRouter(routerImpl: RouterImpl): SplashRouter

    @ApplicationScope
    @Binds
    abstract fun provideAuthRouter(routerImpl: RouterImpl): AuthRouter

    @ApplicationScope
    @Binds
    abstract fun provideNavigateBackRouter(routerImpl: RouterImpl): NavigateBackRouter

    @ApplicationScope
    @Binds
    abstract fun provideMainRouter(routerImpl: RouterImpl): MainRouter

    @ApplicationScope
    @Binds
    abstract fun provideCollectionRouter(routerImpl: RouterImpl): CollectionRouter

    @ApplicationScope
    @Binds
    abstract fun provideFilmDetailsRouter(routerImpl: RouterImpl): FilmDetailsRouter
}