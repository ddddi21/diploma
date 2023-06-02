package com.medicalSystem.common.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.medicalSystem.common.di.scope.ApplicationScope
import com.medicalSystem.common.local.sp.UserSharedPreferences
import com.medicalSystem.common.local.sp.UserSharedPreferencesImpl
import com.medicalSystem.common.resources.ResourceManager
import com.medicalSystem.common.resources.ResourceManagerImpl
import dagger.Module
import dagger.Provides

private const val SHARED_PREFERENCES_NAME_USER = "User"

@Module
class CommonModule {

    @Provides
    @ApplicationScope
    fun provideResourceManager(context: Context): ResourceManager {
        return ResourceManagerImpl(context)
    }

    @Provides
    @ApplicationScope
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME_USER, Context.MODE_PRIVATE)
    }

    @Provides
    @ApplicationScope
    fun provideUserSharedPreferences(sharedPreferences: SharedPreferences): UserSharedPreferences {
        return UserSharedPreferencesImpl(sharedPreferences)
    }
}