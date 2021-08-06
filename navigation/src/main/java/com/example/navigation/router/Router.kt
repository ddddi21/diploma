package com.example.navigation.router

import com.technokratos.auth.router.AuthRouter
import com.technokratos.common.router.NavigateBackRouter
import com.technokratos.splash.SplashRouter

interface Router :
    SplashRouter,
    AuthRouter,
    MainRouter,
    NavigateBackRouter