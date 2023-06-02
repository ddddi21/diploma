package com.medicalSystem.auth.presentation.registration

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.feature_auth.R
import com.medicalSystem.auth.domain.AuthInteractor
import com.medicalSystem.auth.presentation.auth.AuthViewState
import com.medicalSystem.auth.router.AuthRouter
import com.medicalSystem.common.base.BaseViewModel
import com.medicalSystem.common.resources.ResourceManager
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val authRouter: AuthRouter,
    private val interactor: AuthInteractor,
    private val resourceManager: ResourceManager
) : BaseViewModel() {

    private var _authViewState = MutableLiveData<AuthViewState>()
    var authViewState: LiveData<AuthViewState> = _authViewState

    init {
        _authViewState.value = AuthViewState()
    }

    fun onBackToLoginScreenClicked() {
        authRouter.goToPreviousScreen()
    }

    fun onRegistrationButtonClicked(email: String, password: String, repeatPassword: String) {
        if (!checkValidation(email, password, repeatPassword)) {
            return
        }
        viewModelScope.launch {
            _authViewState.value = AuthViewState(email, password, isNeedToShowProgress = true)
            interactor.signUp(email, password).run {
                if (isSuccess) {
                    onSuccessfulRegistration(email, password)
                } else {
                    onFailedRegistrationError(email, password, exceptionOrNull()!!)
                }
            }
        }
    }

    private fun onSuccessfulRegistration(email: String, password: String) {
        _authViewState.value = AuthViewState(email, password)
        authRouter.navigateToMain()
    }

    private fun onFailedRegistrationError(email: String, password: String, throwable: Throwable) {
        _authViewState.value = AuthViewState(
            email,
            password,
            isLoginButtonEnabled = false,
            isLoginErrorMessageVisible = true,
            loginErrorMessage = resourceManager.getString(R.string.login_error_message))
    }

    fun onTextChanged(email: String, password: String, repeatPassword: String) {
        val areEmailAndPasswordFilled = email.isNotEmpty() && password.isNotEmpty() && repeatPassword.isNotEmpty()
        _authViewState.value = AuthViewState(
            email,
            password,
            repeatPassword,
            isLoginButtonEnabled = areEmailAndPasswordFilled
        )
    }

    private fun checkValidation(email: String, password: String, repeatPassword: String): Boolean {
        return when {
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                _authViewState.value = AuthViewState(
                    email,
                    password,
                    repeatPassword,
                    isLoginButtonEnabled = false,
                    isLoginErrorMessageVisible = true,
                    loginErrorMessage = resourceManager.getString(R.string.incorrect_email_error_message)
                )
                false
            }
            password != repeatPassword -> {
                _authViewState.value = AuthViewState(
                    email,
                    password,
                    repeatPassword,
                    isLoginButtonEnabled = false,
                    isLoginErrorMessageVisible = true,
                    loginErrorMessage = resourceManager.getString(R.string.different_passwords_error_message),
                    isSamePassword = false
                )
                false
            }
            else -> true
        }
    }
}