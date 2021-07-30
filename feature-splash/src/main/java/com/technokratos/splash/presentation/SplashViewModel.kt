package com.technokratos.splash.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.technokratos.common.base.BaseViewModel
import com.technokratos.common.utils.Event
import com.technokratos.splash.SplashRouter

class SplashViewModel(
    private val router: SplashRouter
) : BaseViewModel() {

    private val _openUsersEvent = MutableLiveData<Event<Unit>>()
    val openUsersEvent: LiveData<Event<Unit>> = _openUsersEvent

    init {
        _openUsersEvent.value = Event(Unit)
    }

    fun navigateToLogin() {
        router.toLogin()
    }
}