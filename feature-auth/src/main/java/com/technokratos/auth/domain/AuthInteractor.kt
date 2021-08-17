package com.technokratos.auth.domain

import javax.inject.Inject

class AuthInteractor @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend fun signUp(email: String, password: String): Result<Unit> = runCatching {
        authRepository.signUp(email, password)
    }
}