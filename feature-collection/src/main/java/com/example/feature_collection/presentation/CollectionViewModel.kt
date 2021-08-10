package com.example.feature_collection.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.feature_collection.CollectionRouter
import com.technokratos.common.base.BaseViewModel

class CollectionViewModel(
    private var router: CollectionRouter
) : BaseViewModel() {

    private var _isMiniListClicked = MutableLiveData<Boolean>()
    val isMiniListClicked: LiveData<Boolean> = _isMiniListClicked

    fun onMiniListClicked() {
        _isMiniListClicked.value = true
    }
}