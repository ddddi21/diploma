package com.medicalSystem.common.di.modules

import android.content.Context
import com.medicalSystem.common.BuildConfig
import com.medicalSystem.common.data.network.NetworkApiCreator
import com.medicalSystem.common.di.AuthTokenInterceptor
import com.medicalSystem.common.di.AuthorizedRetrofit
import com.medicalSystem.common.di.BaseUrlString
import com.medicalSystem.common.di.LoggingInterceptor
import com.medicalSystem.common.di.OkHttpAuthorizedClient
import com.medicalSystem.common.di.OkHttpUnauthorizedClient
import com.medicalSystem.common.di.UnauthorizedRetrofit
import com.medicalSystem.common.di.scope.ApplicationScope
import com.medicalSystem.common.local.sp.UserSharedPreferences
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT_SECONDS = 10L
private const val WRITE_TIMEOUT_SECONDS = 10L
private const val READ_TIMEOUT_SECONDS = 10L

@Module
class NetworkModule {

    @Provides
    @ApplicationScope
    @BaseUrlString
    fun provideBaseUrl(): String = BuildConfig.AGONA_BACKEND_BASE_URL

    @Provides
    @ApplicationScope
    @LoggingInterceptor
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @ApplicationScope
    @AuthTokenInterceptor
    fun provideAuthTokenInterceptor(userSharedPreference: UserSharedPreferences): Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder()
                .header("Accept", "application/json")
                .header("Authorization", "${userSharedPreference.userAuthToken}")
                .build()
            chain.proceed(request)
        }
    }

    @Provides
    @ApplicationScope
    @OkHttpAuthorizedClient
    fun provideAuthOkHttpClient(
        context: Context,
        @AuthTokenInterceptor authTokenInterceptor: Interceptor,
        @LoggingInterceptor httpLoggingInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(authTokenInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @ApplicationScope
    @OkHttpUnauthorizedClient
    fun provideUnauthOkHttpClient(
        @LoggingInterceptor httpLoggingInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @ApplicationScope
    @UnauthorizedRetrofit
    fun provideUnauthNetworkApiCreator(
        @BaseUrlString baseUrl: String,
        @OkHttpUnauthorizedClient okHttpClient: OkHttpClient
    ): NetworkApiCreator {
        return NetworkApiCreator(okHttpClient, baseUrl)
    }

    @Provides
    @ApplicationScope
    @AuthorizedRetrofit
    fun provideAuthNetworkApiCreator(
        @BaseUrlString baseUrl: String,
        @OkHttpAuthorizedClient okHttpClient: OkHttpClient
    ): NetworkApiCreator {
        return NetworkApiCreator(okHttpClient, baseUrl)
    }
}