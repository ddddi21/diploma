package com.example.feature_main.domain

import com.example.feature_main_api.domain.model.Patient
import javax.inject.Inject

class PatientInteractor @Inject constructor(
    private val patientRepository: PatientRepository
) {

    suspend fun getPatients(): Result<List<Patient>> = runCatching {
       listOf(Patient(0, "Иванов Иван Иванович"), Patient(1, "Иванов Петр Иванович"), Patient(2, "Иванов Артем Иванович"), Patient(3, "Иванов Алексей Иванович"), Patient(4, "Иванов Олег Иванович"), Patient(5, "Иванов Олег Олегович"), Patient(6, "Сидоров Олег Иванович"),Patient(7, "Романов Олег Иванович"),Patient(8, "Сидоров Петр Иванович"),)
    }
    
    suspend fun getDiagnosies(): Result<List<String>> = kotlin.runCatching { 
        listOf("Диабет", "Заболевание почек")
    }
}