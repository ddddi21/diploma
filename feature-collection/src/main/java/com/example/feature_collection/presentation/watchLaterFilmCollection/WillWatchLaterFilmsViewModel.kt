package com.example.feature_collection.presentation.watchLaterFilmCollection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.feature_collection.CollectionRouter
import com.technokratos.common.base.BaseViewModel

class WillWatchLaterFilmsViewModel(
    private var router: CollectionRouter
) : BaseViewModel() {

    private var _isVisibleProgress = MutableLiveData<Boolean>()
    val isVisibleProgress: LiveData<Boolean> = _isVisibleProgress
}