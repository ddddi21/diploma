package com.technokratos.auth.presentation.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.feature_auth.R
import com.technokratos.auth.domain.AuthInteractor
import com.technokratos.auth.router.AuthRouter
import com.technokratos.common.base.BaseViewModel
import com.technokratos.common.resources.ResourceManager
import kotlinx.coroutines.launch

class AuthViewModel(
    private val router: AuthRouter,
    private val interactor: AuthInteractor,
    private val resourceManager: ResourceManager
) : BaseViewModel() {

    private var _authViewState = MutableLiveData<AuthViewState>()
    var authViewState: LiveData<AuthViewState> = _authViewState

    init {
        _authViewState.value = AuthViewState()
    }

    fun onRegistrationClicked() {
        router.navigateToRegistration()
    }

    fun onEnterButtonClicked(email: String, password: String) {
        viewModelScope.launch {
            _authViewState.value = AuthViewState(email, password, isNeedToShowProgress = true)
            interactor.signIn(email, password).run {
                if (isSuccess) {
                    onSuccessfulLogin(email, password)
                } else {
                    Log.d("POZOR", "login failed cause ${exceptionOrNull()}")
                    onFailedLoginError(email, password, exceptionOrNull()!!)
                }
            }
        }
    }

    private fun onSuccessfulLogin(email: String, password: String) {
        _authViewState.value = AuthViewState(email, password)
        Log.d("AWESOME", "Change auth view state from VM: state=${_authViewState.value}")

        router.navigateToMain()
    }

    private fun onFailedLoginError(email: String, password: String, throwable: Throwable) {
        _authViewState.value = AuthViewState(
            email,
            password,
            isLoginButtonEnabled = false,
            isLoginErrorMessageVisible = true,
            loginErrorMessage = resourceManager.getString(R.string.login_error_message))
        Log.d("AWESOME", "Change auth view state: state=${_authViewState.value}")
    }

    fun onTextChanged(email: String, password: String) {
        val areEmailAndPasswordFilled = email.isNotEmpty() && password.isNotEmpty()
        _authViewState.value = AuthViewState(
            email,
            password,
            isLoginButtonEnabled = areEmailAndPasswordFilled
        )
    }
}