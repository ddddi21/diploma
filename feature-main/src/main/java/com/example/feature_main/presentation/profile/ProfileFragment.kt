package com.example.feature_main.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.feature_main.databinding.ProfileFragmentBinding
import com.example.feature_main.di.PatientFeatureComponent
import com.example.feature_main.di.PatientFeatureKey
import com.example.feature_main.presentation.diagnos.DiagnosCheckViewModel
import com.medicalSystem.common.base.BaseFragment
import com.medicalSystem.common.base.adapter.BaseAdapter
import com.medicalSystem.common.di.FeatureUtils

class ProfileFragment : BaseFragment<DiagnosCheckViewModel>() {

    private lateinit var binding: ProfileFragmentBinding

    private val diagnosCheckViewModel by viewModels<DiagnosCheckViewModel>({ requireParentFragment() })

    private val linearIllnessesAdapter = BaseAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProfileFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun inject() {
        FeatureUtils.getFeature<PatientFeatureComponent>(this, PatientFeatureKey::class.java)
            .patientComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun initViews() {
        setUI()
        binding.profileLogout.setOnClickListener {
            viewModel.onLogoutClicked()
        }
        binding.profileAddIteraction.setOnClickListener {
            viewModel.onAddClicked()
        }
    }

    override fun subscribe(viewModel: DiagnosCheckViewModel) {
//        TODO("Not yet implemented")
    }


    private fun setUI() = with(binding) {
//        val option = RequestOptions()
//            .fallback(R.drawable.ic_film_poster_template)
//            .placeholder(R.drawable.ic_film_poster_template)
//        Glide.with(requireContext())
//            .load(film.posterUrl)
//            .apply(option)
    }
}