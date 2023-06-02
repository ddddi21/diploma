package com.medicalSystem.core_db.di

import com.medicalSystem.core_db.AppDatabase

interface DbApi {

    fun provideDatabase(): AppDatabase
}