package com.technokratos.auth.presentation.registration

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.feature_auth.R
import com.technokratos.auth.domain.AuthInteractor
import com.technokratos.auth.presentation.auth.AuthViewState
import com.technokratos.auth.router.AuthRouter
import com.technokratos.common.base.BaseViewModel
import com.technokratos.common.resources.ResourceManager
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

    fun onEnterButtonClicked(email: String, password: String, repeatPassword: String) {
        if (!checkValidation(email, password, repeatPassword).isNullOrEmpty()) {
            return
        }
        viewModelScope.launch {
            _authViewState.value = AuthViewState(email, password, isNeedToShowProgress = true)
            interactor.signUp(email, password).run {
                if (isSuccess) {
                    onSuccessfulRegistration(email, password)
                } else {
                    Log.d("POZOR", "login failed cause ${exceptionOrNull()}")
                    onFailedRegistrationError(email, password, exceptionOrNull()!!)
                }
            }
        }
    }

    private fun onSuccessfulRegistration(email: String, password: String) {
        _authViewState.value = AuthViewState(email, password)
        Log.d("AWESOME", "Change auth view state from VM: state=${_authViewState.value}")

        authRouter.navigateToMain()
    }

    private fun onFailedRegistrationError(email: String, password: String, throwable: Throwable) {
        _authViewState.value = AuthViewState(
            email,
            password,
            isLoginButtonEnabled = false,
            isLoginErrorMessageVisible = true,
            loginErrorMessage = resourceManager.getString(R.string.login_error_message))
        Log.d("AWESOME", "Change auth view state: state=${_authViewState.value}")
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

    private fun checkValidation(email: String, password: String, repeatPassword: String): String? {
        var errorMessage: String? = null
        when {
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                errorMessage = resourceManager.getString(R.string.incorrect_email_error_message)
                _authViewState.value = AuthViewState(
                    email,
                    password,
                    repeatPassword,
                    isLoginButtonEnabled = false,
                    isLoginErrorMessageVisible = true,
                    loginErrorMessage = errorMessage.toString()
                )
            }
            password != repeatPassword -> {
                errorMessage = resourceManager.getString(R.string.different_passwords_error_message)
                _authViewState.value = AuthViewState(
                    email,
                    password,
                    repeatPassword,
                    isLoginButtonEnabled = false,
                    isLoginErrorMessageVisible = true,
                    loginErrorMessage = errorMessage.toString(),
                    isSamePassword = false
                )
            }
        }
        return errorMessage
    }
}