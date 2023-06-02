package com.medicalSystem.auth.router

import com.medicalSystem.common.router.NavigateBackRouter

interface AuthRouter : NavigateBackRouter {

    fun navigateToMain()

    fun navigateToRegistration()
}