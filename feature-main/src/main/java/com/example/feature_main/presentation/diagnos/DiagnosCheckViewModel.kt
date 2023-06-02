package com.example.feature_main.presentation.diagnos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.feature_main.PatientRouter
import com.example.feature_main.domain.PatientInteractor
import com.example.feature_main.presentation.mapper.PatientIntoPresentationPatientMapper
import com.medicalSystem.common.base.BaseViewModel
import com.medicalSystem.common.base.adapter.ViewType
import kotlinx.coroutines.launch

class DiagnosCheckViewModel(
    private var router: PatientRouter,
    private val interactor: PatientInteractor,
    private val patientIntoPresentationPatientMapper: PatientIntoPresentationPatientMapper
) : BaseViewModel() {

    private var _collectionMenuItemMode = MutableLiveData<CollectionMenuItemMode>(
        CollectionMenuItemMode.GRID_LIST
    )
    val collectionMenuItemMode: LiveData<CollectionMenuItemMode> = _collectionMenuItemMode

    private var _linearList = MutableLiveData<List<ViewType>>()
    val linearList: LiveData<List<ViewType>> = _linearList

    fun onMenuItemClicked() {
        _collectionMenuItemMode.value = when (collectionMenuItemMode.value) {
            CollectionMenuItemMode.GRID_LIST -> CollectionMenuItemMode.LINEAR_LIST

            else -> CollectionMenuItemMode.GRID_LIST
        }
    }

    init {
        loadList()
    }

    fun onTextChanged(sg: String, hemo: String, bp: String, al: String, bgr: String) {
//        _authViewState.value = AuthViewState(
//            email,
//            password,
//            isLoginButtonEnabled = email.isNotEmpty() && password.isNotEmpty()
//        )
    }

    private fun loadList() {
        viewModelScope.launch {
            interactor.getDiagnosies().onSuccess { item ->
//                _gridList.value = filmList.map { item ->
//                    filmIntoPresentationFilmMapper.mapIntoGridFilm(item) {
//                        item.status = fragmentType
//                        router.navigateToFilmDetailsScreen(item)
//                    }
//                }
                _linearList.value = item.map { item ->
                    if (item == "Диабет") {
                        patientIntoPresentationPatientMapper.mapIntoLinearDiagnos(item) {
                            router.navigateToDiabet()
                        }
                    } else {
                        patientIntoPresentationPatientMapper.mapIntoLinearDiagnos(item) {
                            router.navigateToKidney()
                        }
                    }

                }
            }
        }
    }

    fun onBackClicked() {
        router.goToPreviousScreen()
    }

    fun onLogoutClicked() {
        router.navigateToAuthNavGraph()
    }

    fun onAddClicked() {
        router.navigateToAddIteraction()
    }
    fun onRefreshSwiped() {
        loadList()
    }
}

enum class CollectionMenuItemMode {
    GRID_LIST,
    LINEAR_LIST
}