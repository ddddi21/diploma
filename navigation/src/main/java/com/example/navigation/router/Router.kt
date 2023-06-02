package com.example.navigation.router

import com.example.feature_main.PatientRouter
import com.example.feature_patient_details.PatientDetailsRouter
import com.medicalSystem.auth.router.AuthRouter
import com.medicalSystem.common.router.NavigateBackRouter
import com.medicalSystem.splash.SplashRouter

interface Router :
    SplashRouter,
    AuthRouter,
    MainRouter,
    NavigateBackRouter,
    PatientRouter,
    PatientDetailsRouter