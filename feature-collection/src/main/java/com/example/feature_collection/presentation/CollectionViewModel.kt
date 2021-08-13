package com.example.feature_collection.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.feature_collection.CollectionRouter
import com.technokratos.common.base.BaseViewModel

class CollectionViewModel(
    private var router: CollectionRouter
) : BaseViewModel() {

    private var _collectionMenuItemMode = MutableLiveData<CollectionMenuItemMode>(CollectionMenuItemMode.GRID_LIST)
    val collectionMenuItemMode: LiveData<CollectionMenuItemMode> = _collectionMenuItemMode

    fun onMenuItemClick() {
        _collectionMenuItemMode.value = when (collectionMenuItemMode.value) {
            CollectionMenuItemMode.GRID_LIST -> CollectionMenuItemMode.LINEAR_LIST

            else -> CollectionMenuItemMode.GRID_LIST
        }
    }
}

enum class CollectionMenuItemMode {
    GRID_LIST,
    LINEAR_LIST
}