package com.example.navigation.router

import com.example.feature_collection.CollectionRouter
import com.example.feature_film_details.FilmDetailsRouter
import com.technokratos.auth.router.AuthRouter
import com.technokratos.common.router.NavigateBackRouter
import com.technokratos.splash.SplashRouter

interface Router :
    SplashRouter,
    AuthRouter,
    MainRouter,
    NavigateBackRouter,
    CollectionRouter,
    FilmDetailsRouter