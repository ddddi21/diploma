package com.technokratos.auth.presentation.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.feature_auth.R
import com.example.feature_auth.databinding.FragmentRegistrationBinding
import com.technokratos.auth.di.registration.RegistrationFeatureKey
import com.technokratos.auth.di.registration.RegistrationFeatureComponent
import com.technokratos.common.base.BaseFragment
import com.technokratos.common.di.FeatureUtils

class RegistrationFragment : BaseFragment<RegistrationViewModel>() {

    private lateinit var binding: FragmentRegistrationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRegistrationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun inject() {
        FeatureUtils.getFeature<RegistrationFeatureComponent>(this, RegistrationFeatureKey::class.java)
            .registrationComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun initViews() {
        with(binding.registrationToolbar) {
            setBackgroundColor(ContextCompat.getColor(context!!, R.color.white))
            setNavigationIcon(R.drawable.ic_navigate_back_arrow)
            setNavigationOnClickListener {
                viewModel.onBackToLoginScreenClicked()
            }
        }
        binding.registrationEnterButton.setOnClickListener {
            viewModel.onEnterButtonClicked()
        }
    }

    override fun subscribe(viewModel: RegistrationViewModel) {
//        TODO("Not yet implemented")
    }
}