package com.medicalSystem.splash.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.medicalSystem.common.base.BaseViewModel
import com.medicalSystem.common.utils.Event
import com.medicalSystem.splash.SplashRouter
import com.medicalSystem.splash.domain.SplashInteractor
import kotlinx.coroutines.launch

class SplashViewModel(
    private val router: SplashRouter,
    private val splashInteractor: SplashInteractor
) : BaseViewModel() {

    private val _openUsersEvent = MutableLiveData<Event<Unit>>()
    val openUsersEvent: LiveData<Event<Unit>> = _openUsersEvent

    init {
        _openUsersEvent.value = Event(Unit)
    }

    fun onAnimationFinished() {
        viewModelScope.launch {
            if (!splashInteractor.isUserLoggedIn()) {
                router.toLogin()
            } else {
                router.navigateToMain()
            }
        }
    }
}