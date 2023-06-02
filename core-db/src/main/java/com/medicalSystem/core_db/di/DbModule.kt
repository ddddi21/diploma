package com.medicalSystem.core_db.di

import android.content.Context
import com.medicalSystem.common.di.scope.ApplicationScope
import com.medicalSystem.core_db.AppDatabase
import com.medicalSystem.core_db.dao.UserDao
import dagger.Module
import dagger.Provides

@Module
class DbModule {

    @Provides
    @ApplicationScope
    fun provideAppDatabase(context: Context): AppDatabase {
        return AppDatabase.get(context)
    }

    @Provides
    @ApplicationScope
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }
}