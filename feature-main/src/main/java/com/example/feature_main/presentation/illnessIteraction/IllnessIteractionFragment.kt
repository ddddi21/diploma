package com.example.feature_main.presentation.illnessIteraction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.coroutineScope
import com.example.feature_main.databinding.DiabetCheckFragmentBinding
import com.example.feature_main.databinding.IllnessIteractionFragmentBinding
import com.example.feature_main.di.PatientFeatureComponent
import com.example.feature_main.di.PatientFeatureKey
import com.example.feature_main.presentation.diagnos.DiagnosCheckViewModel
import com.medicalSystem.common.base.BaseFragment
import com.medicalSystem.common.di.FeatureUtils
import com.medicalSystem.common.utils.makeGone
import com.medicalSystem.common.utils.makeVisible
import com.medicalSystem.common.utils.textChanges
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn

class IllnessIteractionFragment : BaseFragment<DiagnosCheckViewModel>() {

    private lateinit var binding: IllnessIteractionFragmentBinding


    private var isCheckAvailable = false
    private var isTextDone = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = IllnessIteractionFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }

    override fun inject() {
        FeatureUtils.getFeature<PatientFeatureComponent>(this, PatientFeatureKey::class.java)
            .patientComponentFactory()
            .create(this)
            .inject(this)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun initViews() {
        initChangeTextListener()
    }

    override fun subscribe(viewModel: DiagnosCheckViewModel) {

        binding.diabetCheckFragmentCheckResult.setOnClickListener {
            it.makeGone()
            binding.diabetCheckFragmentResult.makeVisible()
        }

    }


    @ExperimentalCoroutinesApi
    private fun initChangeTextListener() = with(binding) {
        combine(
            diabetCheckFragmentAgeInput.textChanges(),
            diabetCheckFragmentBmiInput.textChanges(),
        ) { age, bmi ->
//            viewModel.onTextChanged(sg.toString(),hemo.toString(), bp.toString(), al.toString(), bgr.toString())
            if (!age.isNullOrEmpty() && !bmi.isNullOrEmpty()) {
                isCheckAvailable = true
                isTextDone = true
                diabetCheckFragmentCheckResult.isEnabled = true
            }
        }.launchIn(viewLifecycleOwner.lifecycle.coroutineScope)
    }
}