package com.example.feature_collection.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.feature_collection.CollectionRouter
import com.technokratos.common.base.BaseViewModel

class CollectionViewModel(
    private var router: CollectionRouter
) : BaseViewModel() {

    private var _isNeedToChangeList = MutableLiveData<Boolean>(true)
    val isNeedToChangeList: LiveData<Boolean> = _isNeedToChangeList

    fun onMiniListClicked() {
        _isNeedToChangeList.value = false
    }

    fun onFullListClicked() {
        _isNeedToChangeList.value = true
    }
}