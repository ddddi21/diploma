package com.example.navigation.router

import com.technokratos.auth.router.AuthRouter
import com.technokratos.common.router.NavigateBackRouter
import com.technokratos.splash.SplashRouter
import com.technokratos.users.UsersRouter

interface Router :
    SplashRouter,
    UsersRouter,
    AuthRouter,
    MainRouter,
    NavigateBackRouter