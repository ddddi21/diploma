package com.technokratos.common.di.modules

import android.content.Context
import com.technokratos.auth.data.network.AuthApi
import com.technokratos.common.BuildConfig
import com.technokratos.common.data.network.NetworkApiCreator
import com.technokratos.common.di.AuthTokenInterceptor
import com.technokratos.common.di.AuthorizedCoroutinesRetrofit
import com.technokratos.common.di.AuthorizedRetrofit
import com.technokratos.common.di.BaseUrlString
import com.technokratos.common.di.LoggingInterceptor
import com.technokratos.common.di.OkHttpAuthorizedClient
import com.technokratos.common.di.scope.ApplicationScope
import com.technokratos.common.local.sp.UserSharedPreferences
import com.technokratos.common.resources.ResourceManager
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
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
                .header("Authorization", "Token ${userSharedPreference.userAuthToken}")
                .build()
            chain.proceed(request)
        }
    }

    @Provides
    @ApplicationScope
    @OkHttpAuthorizedClient
    fun provideOkHttpClient(
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
    @AuthorizedRetrofit
    fun provideAuthorizedCoroutinesRetrofit(
        @BaseUrlString baseUrl: String,
        @OkHttpAuthorizedClient okHttpClient: OkHttpClient
    ): NetworkApiCreator {
        return NetworkApiCreator(okHttpClient, baseUrl)
    }

    @Provides
    @ApplicationScope
    fun provideAuthApi(@AuthorizedRetrofit retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }
}