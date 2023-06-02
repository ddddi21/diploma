package com.example.feature_patient_details.data.repository

import com.example.feature_main_api.domain.model.Patient

object PatientMain {

    fun getPatient() = Patient (0, "Иванов Олег Олегович", "22", "M", "Диабет", "Метформин, Диабефарм")
}