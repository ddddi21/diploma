package com.example.feature_main.presentation.nestedFragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.feature_main.PatientRouter
import com.example.feature_main.domain.PatientInteractor
import com.example.feature_main.presentation.mapper.PatientIntoPresentationPatientMapper
import com.example.feature_main_api.domain.model.ViewPagerFragmentType
import com.medicalSystem.common.base.BaseViewModel
import com.medicalSystem.common.base.adapter.ViewType
import kotlinx.coroutines.launch

class PatientsListsViewModel(
    private val router: PatientRouter,
    private val interactor: PatientInteractor,
    private val patientIntoPresentationPatientMapper: PatientIntoPresentationPatientMapper
) : BaseViewModel() {

    private var _gridList = MutableLiveData<List<ViewType>>()
    val gridList: LiveData<List<ViewType>> = _gridList

    private var _linearList = MutableLiveData<List<ViewType>>()
    val linearList: LiveData<List<ViewType>> = _linearList

    private var fragmentType = ViewPagerFragmentType.WILL_WATCH

    fun onViewInited(fragmentType: ViewPagerFragmentType) {
        this.fragmentType = fragmentType
        loadList()
    }

    init {
        loadList()
    }

    private fun loadList() {
        viewModelScope.launch {
            interactor.getPatients().onSuccess { patientList ->
//                _gridList.value = filmList.map { item ->
//                    filmIntoPresentationFilmMapper.mapIntoGridFilm(item) {
//                        item.status = fragmentType
//                        router.navigateToFilmDetailsScreen(item)
//                    }
//                }
                _linearList.value = patientList.map { item ->
                    patientIntoPresentationPatientMapper.mapIntoLinearPatient(item) {
                        router.navigateToPatientDetailsScreen(item)
                    }
                }
            }
        }
    }

    fun onRefreshSwiped() {
        loadList()
    }
}